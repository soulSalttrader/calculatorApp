package com.example.calculatorApp.domain

import com.example.calculatorApp.arguments.TestArgumentsEngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.state.CalculatorState
import com.example.calculatorApp.utils.Constants.MAX_NUM_LENGTH
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.doubles.shouldBeNaN
import io.kotest.matchers.equality.shouldBeEqualToIgnoringFields
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
            activeButton = null,
        )

        engine = EngineStateStandard(engineMath)
        operation = ButtonCalculatorArithmetic.Multiplication
    }

    @Nested
    inner class EnterArithmetic {

        @Test
        fun `should set arithmetic operation when there is no existing operator`() {
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
        fun `should move operandRight to operandLeft and set arithmetic operator`() {
            // Arrange:
            val modifiedState = state.copy(operandRight = "329")
            // Act:
            val newState = engine.enterArithmetic(modifiedState, operation)
            // Assert
            operation shouldBe newState.operator
            modifiedState.operandLeft shouldBe newState.operandRight
        }

        @ParameterizedTest
        @CsvSource("'NaN'")
        fun `should return same state when trying to enter arithmetic on NaN operandRight`(
            operandRight: String
        ) {
            // Arrange:
            val doubleNaN = operandRight.toDoubleOrNull() ?: Double.NaN
            val arrangedState = state.copy(operator = operation, operandRight = doubleNaN.toString())

            // Act:
            val invalidState = engine.enterArithmetic(arrangedState,operation)

            // Assert:
            invalidState.operandRight.toDouble().shouldBeNaN()
        }
    }

    @Nested
    inner class EnterNumber {

        @Test
        fun `should add a number to an empty operandRight`() {
            // Act:
            val newState = engine.enterNumber(state, 329)
            // Assert:
            329 shouldBeEqual newState.operandRight.toInt()
        }

        @Test
        fun `should append a number to the operandRight`() {
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

        @ParameterizedTest
        @CsvSource("'NaN'")
        fun `should return same state when trying to enter number on NaN operandRight`(
            operandRight: String
        ) {
            // Arrange:
            val doubleNaN = operandRight.toDoubleOrNull() ?: Double.NaN
            val arrangedState = state.copy(operator = operation, operandRight = doubleNaN.toString())

            // Act:
            val invalidState = engine.enterNumber(arrangedState,329)

            // Assert:
            invalidState.operandRight.toDouble().shouldBeNaN()
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

        @ParameterizedTest
        @CsvSource("'NaN'")
        fun `should return same state when trying to enter decimal on NaN operandRight`(
            operandRight: String
        ) {
            // Arrange:
            val doubleNaN = operandRight.toDoubleOrNull() ?: Double.NaN
            val arrangedState = state.copy(operator = operation, operandRight = doubleNaN.toString())

            // Act:
            val invalidState = engine.enterDecimal(arrangedState)

            // Assert:
            invalidState.operandRight.toDouble().shouldBeNaN()
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
            newState.activeButton shouldBe null
        }
    }

    @Nested
    inner class ApplyClear {

        private fun provideRepeatableEqualsArguments(): Stream<Arguments> {
            return TestArgumentsEngineState.provideRepeatableEqualsArguments()
        }

        @Test
        fun `should clear operation and restore operandLeft as operandRight if operator exists`() {
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
        fun `should clear operandRight number to zero if operandLeft is empty`() {
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

        @ParameterizedTest
        @CsvSource("'NaN'")
        fun `should return default state when applying clear on NaN operandRight`(
            operandRight: String
        ) {
            // Arrange:
            val doubleNaN = operandRight.toDoubleOrNull() ?: Double.NaN
            val arrangedState = state.copy(operandLeft = doubleNaN.toString(), operator = operation, operandRight = doubleNaN.toString())

            // Act:
            val stateCleared = engine.applyClear(arrangedState)

            // Assert:
            state shouldBe stateCleared
        }

        @ParameterizedTest
        @MethodSource("provideRepeatableEqualsArguments")
        fun `should return default state when applying clear on equal repeatable`(
            operandLeft: Double,
            operation: ButtonCalculatorArithmetic,
            operandRight: Double,
        ) {
            // Arrange:
            val arrangedState = state.copy(
                operandRight = operandRight.toString(),
                operandLeft = operandLeft.toString(),
                operator = operation
            )

            // Act & Assert:
            var stateEqual = arrangedState
            for (i in 1..10) { stateEqual = engine.applyEquals(stateEqual) }

            val stateCleared = engine.applyClear(stateEqual)

            // Assert:
            state.shouldBeEqualToIgnoringFields(stateCleared, CalculatorState::activeButton)
        }
    }

    @Nested
    inner class ApplyArithmetic {

        fun provideArguments(): Stream<Arguments> {
            return TestArgumentsEngineState.provideArithmeticArguments()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should correctly apply equals operation and update state`(
            operandLeft: Double,
            operation: ButtonCalculatorArithmetic,
            operandRight: Double,
            expected: Double,
        ) {
            // Arrange:
            val arrangedState = state.copy(
                operandRight = operandRight.toString(),
                operandLeft = operandLeft.toString(),
                operator = operation
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
            "'NaN', 5", // Invalid operandLeft (NaN)
        )
        fun `should return same state if previousNumber is not a valid number`(
            operandLeft: String,
            operandRight: String,
        ) {
            // Arrange:
            val doubleNaN = operandLeft.toDoubleOrNull() ?: Double.NaN
            val arrangedState = state.copy(
                operandRight = operandRight,
                operandLeft = doubleNaN.toString(),
                operator = operation
            )

            // Act:
            val newState = engine.applyArithmetic(arrangedState)

            // Assert:
            newState shouldBe arrangedState
        }

        @ParameterizedTest
        @CsvSource(
            "5, 'NaN'",  // Invalid operandRight (NaN)
        )
        fun `should return same state if operandRight is not a valid number`(
            operandLeft: String,
            operandRight: String,
        ) {
            // Arrange:
            val doubleNaN = operandRight.toDoubleOrNull() ?: Double.NaN
            val arrangedState = state.copy(
                operandRight = doubleNaN.toString(),
                operandLeft = operandLeft,
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
        fun `should correctly applyPercent operation`(
            number: Double,
            expectedResult: Double
        ) {
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
        fun `should correctly apply percent operation`(number: Double, expectedResult: Double) {
            // Arrange:
            state = state.copy(operandRight = number.toString())

            // Act:
            val newState = engine.applyPercent(state)

            // Assert:
            newState.operandRight shouldBe expectedResult.toString()
        }
    }

    @Nested
    inner class ApplyEquals {

        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsEngineState.provideArithmeticArguments()
        }

        private fun provideRepeatableEqualsArguments(): Stream<Arguments> {
            return TestArgumentsEngineState.provideRepeatableEqualsArguments()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should correctly apply equals operation`(
            operandLeft: Double,
            operation: ButtonCalculatorArithmetic,
            operandRight: Double,
            expected: Double,
        ) {
            // Arrange:
            val arrangedState = state.copy(
                operandRight = operandRight.toString(),
                operandLeft = operandLeft.toString(),
                operator = operation,
            )

            // Act:
            val newState = engine.applyEquals(arrangedState)

            // Assert:
            newState.operandRight shouldBe expected.toString()
            newState.operandLeft shouldBe expected.toString()
            newState.operator shouldBe arrangedState.operator
        }

        @ParameterizedTest
        @CsvSource(
            "'NaN', 5",
            "5, 'NaN'",
        )
        fun `should return same state if operandRight or operandLeft are not a valid number`(
            operandLeft: String,
            operandRight: String,
        ) {
            // Arrange:
            val doubleNaN = operandRight.toDoubleOrNull() ?: Double.NaN
            val arrangedState = state.copy(
                operandRight = doubleNaN.toString(),
                operandLeft = operandLeft,
            )

            // Act:
            val newState = engine.applyEquals(arrangedState)

            // Assert:
            newState.operandRight shouldBe arrangedState.operandRight
            newState.operandLeft shouldBe arrangedState.operandLeft
            newState.operator shouldBe arrangedState.operator
        }

        @ParameterizedTest
        @MethodSource("provideRepeatableEqualsArguments")
        fun `should perform repeatable equals correctly`(
            operandLeft: Double,
            operation: ButtonCalculatorArithmetic,
            operandRight: Double,
            expected: Double
        ) {
            // Arrange:
            val arrangedState = state.copy(
                operandRight = operandRight.toString(),
                operandLeft = operandLeft.toString(),
                operator = operation
            )

            // Act & Assert:
            var state = arrangedState
            for (i in 1..10) { state = engine.applyEquals(state) }

            // Assert:
            state.operandRight.toDouble().shouldBe(expected plusOrMinus (1e-9))
            state.operandLeft.toDouble().shouldBe(expected plusOrMinus (1e-9))
            state.operand?.toDouble().shouldBe(operandRight plusOrMinus (1e-9))
            state.operator shouldBe arrangedState.operator
        }
    }
}