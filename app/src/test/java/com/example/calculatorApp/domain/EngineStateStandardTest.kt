package com.example.calculatorApp.domain

import com.example.calculatorApp.arguments.TestArgumentsEngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.state.CalculatorState
import com.example.calculatorApp.utils.Constants.MAX_NUM_LENGTH
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotMatch
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class EngineStateStandardTest {

    private lateinit var state: CalculatorState
    private lateinit var engine: EngineState
    private lateinit var operation: ButtonCalculatorArithmetic
    private val engineMath = EngineMathStandard()

    @BeforeEach
    fun setUp() {
        // Arrange:
        state = CalculatorState(
            operandRight = "0",
            operator  = null,
            operandLeft = "",
            activeLabel = ""
        )

        engine = EngineStateStandard(engineMath)
        operation = ButtonCalculatorArithmetic.Multiplication
    }

    @Nested
    inner class EnterArithmetic {

        @Test
        fun `should set arithmetic operation when there is no existing operation`() {
            // Act:
            val newState = engine.enterArithmetic(state, operation)
            // Assert:
            operation shouldBe newState.operator
        }

        @Test
        fun `should replace arithmetic operation if one already exists`() {
            // Arrange:
            val modifiedState = state.copy(operator = ButtonCalculatorArithmetic.Addition)
            // Act:
            val newState = engine.enterArithmetic(modifiedState, operation)
            // Assert
            operation shouldBe newState.operator
        }

        @Test
        fun `should move current number to previous number and set arithmetic operation`() {
            // Arrange:
            val modifiedState = state.copy(operandRight = "329")
            // Act:
            val newState = engine.enterArithmetic(modifiedState, operation)
            // Assert
            operation shouldBe newState.operator
            modifiedState.operandLeft shouldBe newState.operandRight
        }
    }

    @Nested
    inner class EnterNumber {

        @Test
        fun `should add a number to an empty current number`() {
            // Act:
            val newState = engine.enterNumber(state, 329)
            // Assert:
            329 shouldBeEqual newState.operandRight.toInt()
        }

        @Test
        fun `should append a number to the current number`() {
            // Arrange:
            val modifiedState = state.copy(operandRight = "329")
            // Act:
            val newState = engine.enterNumber(modifiedState, 55)
            // Assert:
            32955 shouldBeEqual newState.operandRight.toInt()
        }

        @Test
        fun `should not add a number if max length is reached`() {
            // Arrange:
            val modifiedState = state.copy(operandRight = "1".repeat(MAX_NUM_LENGTH))
            // Act:
            val newState = engine.enterNumber(modifiedState, 55)
            // Assert:
            1111111111 shouldBeEqual newState.operandRight.toInt()
        }
    }

    @Nested
    inner class EnterDecimal {

        @Test
        fun `should add a decimal point if not already present`() {
            // Act:
            val newState = engine.enterDecimal(state)
            // Assert:
            newState.operandRight.shouldContain(".")
        }

        @Test
        fun `should not add a decimal point if already present`() {
            // Arrange:
            val modifiedState = state.copy(operandRight = "32.9")
            // Act:
            val newState = engine.enterDecimal(modifiedState)
            // Assert:
            newState.operandRight.shouldNotMatch("32.9.")
        }
    }

    @Nested
    inner class ApplyClearAll {

        @Test
        fun `should reset calculator state to default`() {
            // Arrange:
            val modifiedState = state.copy(
                operandRight = "32.9",
                operandLeft = "123",
                operator = ButtonCalculatorArithmetic.Multiplication
            )
            // Act:
            val newState = engine.applyClearAll(modifiedState)

            // Assert:
            newState.operandRight shouldBe "0"
            newState.operator shouldBe null
            newState.operandLeft shouldBe ""
            newState.activeLabel shouldBe ""
        }
    }

    @Nested
    inner class ApplyClear {

        @Test
        fun `should clear operation and restore previous number as current number if operation exists`() {
            // Arrange:
            val modifiedState = state.copy(
                operandRight = "329",
                operandLeft = "111",
                operator = ButtonCalculatorArithmetic.Multiplication
            )
            // Act:
            val newState = engine.applyClear(modifiedState)
            // Assert:
            "111" shouldBe newState.operandRight
        }

        @Test
        fun `should reset current number to zero if previous number is empty`() {
            // Arrange:
            val modifiedState = state.copy(
                operandRight = "329",
                operandLeft = "",
                operator = null
            )
            // Act:
            val newState = engine.applyClear(modifiedState)
            // Assert:
            "0" shouldBe newState.operandRight
        }
    }

    @Nested
    inner class ApplyArithmetic {

        fun provideArguments(): Stream<Arguments> {
            return TestArgumentsEngineState.provideArithmeticArguments()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should execute applyArithmetic on engine and update state`(
            arithmetic: ButtonCalculatorArithmetic,
            left: Double,
            right: Double,
            expected: Double,
        ) {
            // Arrange:
            val arrangedState = state.copy(
                operandRight = right.toString(),
                operandLeft = left.toString(),
                operator = arithmetic
            )

            // Act:
            val newState = engine.applyArithmetic(arrangedState)

            // Assert:
            newState.operandRight shouldBe expected.toString()
            newState.operandLeft shouldBe ""
            newState.operator shouldBe null
        }

        @ParameterizedTest
        @CsvSource(
            "'NaN', 5", // Invalid previousNumber (NaN)
        )
        fun `should return same state if previousNumber is not a valid number`(
            left: String,
            right: String,
        ) {
            // Arrange:
            val arrangedState = state.copy(
                operandRight = right,
                operandLeft = left,
                operator = operation
            )

            // Act:
            val newState = engine.applyArithmetic(arrangedState)

            // Assert:
            newState shouldBe arrangedState
        }

        @ParameterizedTest
        @CsvSource(
            "5, 'NaN'",  // Invalid currentNumber (NaN)
        )
        fun `should return same state if currentNumber is not a valid number`(
            left: String,
            right: String,
        ) {
            // Arrange:
            val arrangedState = state.copy(
                operandRight = right,
                operandLeft = left,
                operator = operation
            )

            // Act:
            val newState = engine.applyArithmetic(arrangedState)

            // Assert:
            newState shouldBe arrangedState
        }

        @Test
        fun `should return same state if operation is null`() {
            // Arrange:
            val arrangedState = state.copy(
                operandRight = "5",
                operandLeft = "",
                operator = null
            )

            // Act:
            val newState = engine.applyArithmetic(arrangedState)

            // Assert:
            newState shouldBe arrangedState
        }
    }

    @Nested
    inner class ApplySign {

        @ParameterizedTest
        @CsvSource(
            "500.0, -500.0",
            "5.0, -5.0",
            "0.0, -0.0",
            "-100, 100"
        )
        fun `should applyPercent on engine`(number: Double, expectedResult: Double) {
            // Arrange:
            state = state.copy(operandRight = number.toString())

            // Act:
            val newState = engine.applySign(state)

            // Assert:
            newState.operandRight shouldBe expectedResult.toString()
        }

    }

    @Nested
    inner class ApplyPercent {
        @ParameterizedTest
        @CsvSource(
            "500.0, 5.0",
            "5.0, 0.05",
            "0.0, 0.0",
            "-100, -1"
        )
        fun `should applyPercent`(number: Double, expectedResult: Double) {
            // Arrange:
            state = state.copy(operandRight = number.toString())

            // Act:
            val newState = engine.applyPercent(state)

            // Assert:
            newState.operandRight shouldBe expectedResult.toString()
        }
    }
}