package com.example.calculatorApp.domain

import com.example.calculatorApp.arguments.TestArgumentsEngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
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
    private lateinit var arithmetic: ButtonCalculatorBinary
    private val engineMath = EngineMathStandard()

    @BeforeEach
    fun setUp() {
        // Arrange:
        state = CalculatorState(
            lastInput = "0",
            lastOperator  = null,
            expression = emptyList(),
            activeButton = null,
        )

        engine = EngineStateStandard(engineMath)
        arithmetic = ButtonCalculatorBinary.Multiplication
    }

    @Nested
    inner class HandleArithmetic {

        @ParameterizedTest
        @CsvSource(
            "5, 'NaN'", // Invalid lastInput (NaN)
        )
        fun `should return the same state if lastInput is not a valid number`(
            expression: String,
            lastInput: String,
        ) {
            // Arrange:
            val operandRightOrNaN = lastInput.toDoubleOrNull() ?: Double.NaN
            val initialState = state.copy(expression = listOf(expression), lastInput = operandRightOrNaN.toString())

            // Act:
            val stateNaN = engine.handleArithmetic(initialState, arithmetic)

            // Assert:
            stateNaN shouldBe initialState
        }

        @ParameterizedTest
        @CsvSource(
            "5, 7",
            "-1, 0.7",
            "0, 0"
        )
        fun `should reset lastInput and operand if Equals was last pressed`(
            expression: String,
            lastInput: String,
        ) {
            // Arrange:
            val initialState = CalculatorState(
                expression = listOf (expression, arithmetic.toString()),
                lastInput = lastInput,
                lastOperator = arithmetic,
                lastResult = lastInput,
                activeButton = ButtonCalculatorControl.Equals
            )

            // Act:
            val newState = engine.handleArithmetic(initialState, arithmetic)

            // Assert:
            newState.shouldBeEqualToIgnoringFields(
                initialState.copy(
                    lastOperator = arithmetic,
                    lastInput = "",
                    lastResult = null,
                ),
                CalculatorState::activeButton
            )
        }

        @ParameterizedTest
        @CsvSource(
            "5, 7",
            "-1, 0.7",
            "0, 0"
        )
        fun `should apply the previous arithmetic operation before updating the lastOperator`(
            expression: String,
            lastInput: String,
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = listOf (expression, arithmetic.toString()),
                lastOperator = arithmetic,
                lastInput = lastInput,
                lastResult = lastInput,
                activeButton = arithmetic,
            )

            val expectedState = engine.applyArithmetic(initialState)
                .copy(
                    expression = listOf(engine.applyArithmetic(initialState).lastInput, arithmetic.symbol.label),
                    lastOperator = arithmetic,
                    lastInput = "",
                    activeButton = arithmetic
                )

            // Act:
            val newState = engine.handleArithmetic(initialState, arithmetic)


            // Assert:
            newState shouldBe expectedState
        }

        @ParameterizedTest
        @CsvSource(
            "5, ''",
            "-1, ''",
            "0, ''"
        )
        fun `should update lastOperator without applying arithmetic if lastInput is empty`(
            expression: String,
            lastInput: String,
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = listOf (expression),
                lastInput = lastInput,
            )

            val expectedState = initialState.copy(
                    expression = listOf(expression),
                    lastOperator = engine.enterArithmetic(initialState, arithmetic).lastOperator,
                    lastInput = lastInput,
                    activeButton = engine.enterArithmetic(initialState, arithmetic).activeButton,
                )

            // Act:
            val newState = engine.handleArithmetic(initialState, arithmetic)


            // Assert:
            newState shouldBe expectedState
        }

        @ParameterizedTest
        @CsvSource(
            "5, 0",
            "-1, 0",
            "0, 0"
        )
        fun `should return the expression NaN if division by zero is attempted`(
            expression: String,
            lastInput: String,
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = listOf (expression, ButtonCalculatorBinary.Division.symbol.label),
                lastOperator = ButtonCalculatorBinary.Division,
                lastInput = lastInput
            )

            // Act:
            val stateNaN = engine.handleArithmetic(initialState, ButtonCalculatorBinary.Division)

            // Assert:
            stateNaN.expression[0] shouldBe Double.NaN.toString()
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
            expression: Double,
            operation: ButtonCalculatorBinary,
            lastInput: Double,
            expected: Double,
        ) {
            // Arrange:
            val initialState = state.copy(
                lastInput = lastInput.toString(),
                expression = listOf(expression.toString(), operation.toString()),
                lastOperator = operation
            )

            // Act:
            val newState = engine.applyArithmetic(initialState)

            newState.also { println(it) }

            // Assert:
            newState.lastInput shouldBe expected.toString()
            newState.lastResult shouldBe expected.toString()
            newState.expression shouldBe listOf("$expression")
            newState.lastOperator shouldBe null
        }

        @ParameterizedTest
        @CsvSource(
            "'NaN', 5", // Invalid expression (NaN)
        )
        fun `should return same state if previousNumber is not a valid number`(
            expression: String,
            lastInput: String,
        ) {
            // Arrange:
            val operandRightOrNaN = expression.toDoubleOrNull() ?: Double.NaN
            val initialState = state.copy(
                lastInput = lastInput,
                expression = listOf(operandRightOrNaN.toString()),
                lastOperator = arithmetic
            )

            // Act:
            val newState = engine.applyArithmetic(initialState)

            // Assert:
            newState shouldBe initialState
        }

        @ParameterizedTest
        @CsvSource(
            "5, 'NaN'",  // Invalid lastInput (NaN)
        )
        fun `should return same state if lastInput is not a valid number`(
            expression: String,
            lastInput: String,
        ) {
            // Arrange:
            val operandRightOrNaN = lastInput.toDoubleOrNull() ?: Double.NaN
            val initialState = state.copy(
                lastInput = operandRightOrNaN.toString(),
                expression = listOf(expression),
                lastOperator = arithmetic
            )

            // Act:
            val newState = engine.applyArithmetic(initialState)

            // Assert:
            newState shouldBe initialState
        }

        @Test
        fun `should return same state if operation is null`() {
            // Arrange:
            val initialState = state.copy(
                lastInput = "5",
                expression = emptyList(),
                lastOperator = null
            )

            // Act:
            val newState = engine.applyArithmetic(initialState)

            // Assert:
            newState shouldBe initialState
        }
    }

    @Nested
    inner class EnterArithmetic {

        @Test
        fun `should set arithmetic operation when there is no existing lastOperator`() {
            // Act:
            val newState = engine.enterArithmetic(state, arithmetic)
            // Assert:
            arithmetic shouldBe newState.lastOperator
        }

        @Test
        fun `should replace arithmetic operation if one already exists`() {
            // Arrange:
            val initialState = state.copy(lastOperator = ButtonCalculatorBinary.Addition)
            // Act:
            val newState = engine.enterArithmetic(initialState, arithmetic)
            // Assert
            arithmetic shouldBe newState.lastOperator
        }

        @Test
        fun `should move lastInput to expression and set arithmetic lastOperator`() {
            // Arrange:
            val initialState = state.copy(lastInput = "329")
            // Act:
            val newState = engine.enterArithmetic(initialState, arithmetic)
            // Assert
            arithmetic shouldBe newState.lastOperator
            initialState.expression.isEmpty() shouldBe newState.lastInput.isEmpty()
        }

        @ParameterizedTest
        @CsvSource("'NaN'")
        fun `should return same state when trying to enter arithmetic on NaN lastInput`(
            lastInput: String
        ) {
            // Arrange:
            val operandRightOrNaN = lastInput.toDoubleOrNull() ?: Double.NaN
            val initialState = state.copy(lastOperator = arithmetic, lastInput = operandRightOrNaN.toString())

            // Act:
            val invalidState = engine.enterArithmetic(initialState,arithmetic)

            // Assert:
            invalidState.lastInput.toDouble().shouldBeNaN()
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
            expression: Double,
            operation: ButtonCalculatorBinary,
            lastInput: Double,
            expected: Double,
        ) {
            // Arrange:
            val initialState = state.copy(
                lastInput = lastInput.toString(),
                expression = listOf(expression.toString(), operation.toString()),
                lastOperator = operation,
            )

            // Act:
            val newState = engine.applyEquals(initialState)

            // Assert:
            newState.lastInput shouldBe expected.toString()
            newState.expression[0] shouldBe expected.toString()
            newState.lastOperator shouldBe initialState.lastOperator
        }

        @ParameterizedTest
        @CsvSource(
            "'NaN', 5",
            "5, 'NaN'",
        )
        fun `should return same state if lastInput or expression are not a valid number`(
            expression: String,
            lastInput: String,
        ) {
            // Arrange:
            val operandRightOrNaN = lastInput.toDoubleOrNull() ?: Double.NaN
            val initialState = state.copy(
                lastInput = operandRightOrNaN.toString(),
                expression = listOf(expression),
            )

            // Act:
            val newState = engine.applyEquals(initialState)

            // Assert:
            newState.lastInput shouldBe initialState.lastInput
            newState.expression shouldBe initialState.expression
            newState.lastOperator shouldBe initialState.lastOperator
        }

        @ParameterizedTest
        @MethodSource("provideRepeatableEqualsArguments")
        fun `should perform repeatable equals correctly`(
            expression: Double,
            operation: ButtonCalculatorBinary,
            lastInput: Double,
            expected: Double
        ) {
            // Arrange:
            val initialState = state.copy(
                lastInput = lastInput.toString(),
                expression = listOf(expression.toString(), operation.toString()),
                lastOperator = operation
            )

            // Act & Assert:
            var state = initialState
            for (i in 1..10) { state = engine.applyEquals(state) }

            state.also { println(it) }

            // Assert:
            state.lastInput.toDouble().shouldBe(expected plusOrMinus (1e-9))
//            state.expression[0].toDouble().shouldBe(expected plusOrMinus (1e-9))
//            state.lastResult?.toDouble().shouldBe(lastInput plusOrMinus (1e-9))
            state.lastOperator shouldBe initialState.lastOperator
        }

        @ParameterizedTest
        @CsvSource(
            "22.0, 3.0, 22.66",
            "329.0, 100.0, 658.0",
            "1000.0, 100.0, 2000.0",
            "-1.0, 50.0, -1.5",
            "-329.0, 1.0, -332.29",
            "-1000.0, 0.0, -1000.0",
            "0.0, 0.0, 0.0",
            "null, 22.0, 0.22",
        )
        fun `should correctly apply equals operation after percentage is applied`(
            expression: String,
            lastInput: String,
            expectedNumber: String,
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = listOf(expression, ButtonCalculatorBinary.Addition.toString()),
                lastOperator = ButtonCalculatorBinary.Addition,
                lastInput = lastInput,
            )

            initialState.also { println(it) }

            // Act
            val newState = engine.applyPercent(initialState)

            newState.also { println(it) }

            val resultState = engine.applyEquals(newState)

            // Assert:
            expectedNumber shouldBe resultState.lastInput
        }
    }

    @Nested
    inner class EnterNumber {

        @Test
        fun `should add a number to an empty lastInput`() {
            // Act:
            val newState = engine.enterNumber(state, 329)
            // Assert:
            329 shouldBeEqual newState.lastInput.toInt()
        }

        @Test
        fun `should append a number to the lastInput`() {
            // Arrange:
            val initialState = state.copy(lastInput = "329")
            // Act:
            val newState = engine.enterNumber(initialState, 55)
            // Assert:
            32955 shouldBeEqual newState.lastInput.toInt()
        }

        @Test
        fun `should not add a number if max length is reached`() {
            // Arrange:
            val initialState = state.copy(lastInput = "1".repeat(MAX_NUM_LENGTH))
            // Act:
            val newState = engine.enterNumber(initialState, 55)
            // Assert:
            1111111111 shouldBeEqual newState.lastInput.toInt()
        }

        @ParameterizedTest
        @CsvSource(
            "23,'NaN'",
        )
        fun `should return same state when trying to enter number on NaN lastInput`(
            expression: String,
            lastInput: String
        ) {
            // Arrange:
            val operandRightOrNaN = lastInput.toDoubleOrNull() ?: Double.NaN
            val initialState = state.copy(expression = listOf(expression), lastOperator = arithmetic, lastInput = operandRightOrNaN.toString())

            // Act:
            val newState = engine.enterNumber(initialState,329)

            // Assert:
            initialState shouldBe newState
        }


        @ParameterizedTest
        @CsvSource(
            "'NaN', 23",
        )
        fun `should return same state when trying to enter number on NaN expression`(
            expression: String,
            lastInput: String
        ) {
            // Arrange:
            val operandLeftOrNaN = lastInput.toDoubleOrNull() ?: Double.NaN
            val initialState = state.copy(expression = listOf(operandLeftOrNaN.toString()), lastOperator = arithmetic, lastInput = lastInput)

            // Act:
            val newState = engine.enterNumber(initialState,329)

            // Assert:
            initialState.shouldBeEqualToIgnoringFields(newState, CalculatorState::lastInput)
        }
    }

    @Nested
    inner class EnterDecimal {

        @Test
        fun `should add a decimal point if not already present`() {
            // Act:
            val newState = engine.enterDecimal(state)
            // Assert:
            newState.lastInput.shouldContain(".")
        }

        @Test
        fun `should not add a decimal point if already present`() {
            // Arrange:
            val initialState = state.copy(lastInput = "32.9")
            // Act:
            val newState = engine.enterDecimal(initialState)
            // Assert:
            newState.lastInput.shouldNotMatch("32.9.")
        }

        @Test
        fun `should add a decimal point and zero if expression is not present`() {
            // Act:
            val newState = engine.enterDecimal(state)
            // Assert:
            newState.lastInput.shouldContain("0.")
        }

        @ParameterizedTest
        @CsvSource(
            "23,'NaN'",
            "'NaN', 23"
        )
        fun `should return same state when trying to enter decimal on NaN lastInput`(
            expression: String,
            lastInput: String
        ) {
            // Arrange:
            val operandRightOrNaN = lastInput.toDoubleOrNull()
            val operandLeftOrNaN = expression.toDoubleOrNull()
            val initialState = state.copy(expression = listOf(operandLeftOrNaN.toString()), lastOperator = arithmetic, lastInput = operandRightOrNaN.toString())

            // Act:
            val newState = engine.enterDecimal(initialState)

            // Assert:
            newState shouldBe initialState
        }
    }

    @Nested
    inner class ApplyClearAll {

        @Test
        fun `should reset calculator state to default`() {
            // Arrange:
            val initialState = state.copy(
                lastInput = "32.9",
                expression = listOf("123"),
                lastOperator = ButtonCalculatorBinary.Multiplication
            )
            // Act:
            val newState = engine.applyClearAll(initialState)

            // Assert:
            newState.lastInput shouldBe "0"
            newState.lastOperator shouldBe null
            newState.expression shouldBe emptyList()
            newState.activeButton shouldBe null
        }
    }

    @Nested
    inner class ApplyClear {

        private fun provideRepeatableEqualsArguments(): Stream<Arguments> {
            return TestArgumentsEngineState.provideRepeatableEqualsArguments()
        }

        @Test
        fun `should clear operation and restore expression as lastInput if lastOperator exists`() {
            // Arrange:
            val initialState = state.copy(
                lastInput = "329",
                expression = listOf("329, +"),
                lastOperator = ButtonCalculatorBinary.Multiplication
            )
            // Act:
            val newState = engine.applyClear(initialState)
            // Assert:
            "329" shouldBe newState.lastInput
        }

        @Test
        fun `should clear lastInput number to zero if expression is empty`() {
            // Arrange:
            val initialState = state.copy(
                lastInput = "329",
                expression = emptyList(),
                lastOperator = null
            )
            // Act:
            val newState = engine.applyClear(initialState)
            // Assert:
            "0" shouldBe newState.lastInput
        }

        @ParameterizedTest
        @CsvSource("'NaN'")
        fun `should return default state when applying clear on NaN lastInput`(
            lastInput: String
        ) {
            // Arrange:
            val operandRightOrNaN = lastInput.toDoubleOrNull() ?: Double.NaN
            val initialState = state.copy(expression = listOf(operandRightOrNaN.toString()), lastOperator = arithmetic, lastInput = operandRightOrNaN.toString())

            // Act:
            val stateCleared = engine.applyClear(initialState)

            // Assert:
            state shouldBe stateCleared
        }

        @ParameterizedTest
        @MethodSource("provideRepeatableEqualsArguments")
        fun `should return default state when applying clear on equal repeatable`(
            expression: Double,
            operation: ButtonCalculatorBinary,
            lastInput: Double,
        ) {
            // Arrange:
            val initialState = state.copy(
                lastInput = lastInput.toString(),
                expression = listOf(expression.toString(), operation.toString()),
                lastOperator = operation
            )

            // Act & Assert:
            var stateEqual = initialState
            for (i in 1..10) { stateEqual = engine.applyEquals(stateEqual) }

            val stateCleared = engine.applyClear(stateEqual)

            // Assert:
            state.shouldBeEqualToIgnoringFields(stateCleared, CalculatorState::activeButton)
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
            state = state.copy(lastInput = number.toString())

            // Act:
            val newState = engine.applySign(state)

            // Assert:
            newState.lastInput shouldBe expectedResult.toString()
        }
    }

    @Nested
    inner class ApplyPercentage {
        @ParameterizedTest
        @CsvSource(
            "500.0, 5.0",
            "5.0, 0.05",
            "0.0, 0.0",
            "-100, -1"
        )
        fun `should correctly apply percent operation`(number: Double, expectedResult: Double) {
            // Arrange:
            state = state.copy(lastInput = number.toString())

            // Act:
            val newState = engine.applyPercent(state)

            // Assert:
            newState.lastInput shouldBe expectedResult.toString()
        }
    }
}