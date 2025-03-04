package com.example.calculatorApp.domain

import com.example.calculatorApp.arguments.TestArgumentsEngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
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
    private val engineMath = EngineMathStandard()

    @BeforeEach
    fun setUp() {
        // Arrange:
        state = CalculatorState(
            expression = emptyList(),
            lastOperand = "0",
            lastOperator  = null,
            activeButton = null,
        )

        engine = EngineStateStandard(engineMath)
    }

    @Nested
    inner class HandleBinary {

        private lateinit var binary: ButtonCalculatorBinary

        @BeforeEach
        fun setUp() {
            binary = ButtonCalculatorBinary.Multiplication
        }

        @ParameterizedTest
        @CsvSource(
            "5, 'NaN'", // Invalid lastOperand (NaN)
        )
        fun `should return the same state if lastOperand is not a valid number`(
            expression: String,
            lastOperand: String,
        ) {
            // Arrange:
            val operandRightOrNaN = lastOperand.toDoubleOrNull() ?: Double.NaN
            val initialState = state.copy(expression = listOf(expression), lastOperand = operandRightOrNaN.toString())

            // Act:
            val stateNaN = engine.handleBinary(initialState, binary)

            // Assert:
            stateNaN shouldBe initialState
        }

        @ParameterizedTest
        @CsvSource(
            "5, 7",
            "-1, 0.7",
            "0, 0"
        )
        fun `should reset lastOperand and operand if Equals was last pressed`(
            expression: String,
            lastOperand: String,
        ) {
            // Arrange:
            val initialState = CalculatorState(
                expression = listOf (expression, binary.toString()),
                lastOperand = lastOperand,
                lastOperator = binary,
                lastResult = lastOperand,
                activeButton = ButtonCalculatorControl.Equals
            )

            // Act:
            val newState = engine.handleBinary(initialState, binary)

            // Assert:
            newState.shouldBeEqualToIgnoringFields(
                initialState.copy(
                    lastOperator = binary,
                    lastOperand = "",
                    lastResult = null,
                ),
                CalculatorState::activeButton
            )
        }

        @ParameterizedTest
        @CsvSource(
            "5, ''",
            "-1, ''",
            "0, ''"
        )
        fun `should update lastOperator without applying arithmetic if lastOperand is empty`(
            expression: String,
            lastOperand: String,
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = listOf (expression),
                lastOperand = lastOperand,
            )

            val expectedState = initialState.copy(
                    expression = listOf(expression),
                    lastOperator = engine.handleBinary(initialState, binary).lastOperator,
                    lastOperand = lastOperand,
                    activeButton = engine.handleBinary(initialState, binary).activeButton,
                )

            // Act:
            val newState = engine.handleBinary(initialState, binary)

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
            lastOperand: String,
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = listOf (expression, ButtonCalculatorBinary.Division.symbol.label),
                lastOperator = ButtonCalculatorBinary.Division,
                lastOperand = lastOperand
            )

            // Act:
            val stateNaN = engine.handleBinary(initialState, ButtonCalculatorBinary.Division)

            // Assert:
            stateNaN.expression[0] shouldBe Double.NaN.toString()
        }

        @Nested
        inner class ApplyBinary {

            private lateinit var binary: ButtonCalculatorBinary

            @BeforeEach
            fun setUp() {
                binary = ButtonCalculatorBinary.Multiplication
            }

            fun provideArguments(): Stream<Arguments> {
                return TestArgumentsEngineState.provideArithmeticArguments()
            }

            @ParameterizedTest
            @MethodSource("provideArguments")
            fun `should correctly apply equals operation and update state`(
                expression: Double,
                operation: ButtonCalculatorBinary,
                lastOperand: Double,
                expected: Double,
            ) {
                // Arrange:
                val initialState = state.copy(
                    lastOperand = lastOperand.toString(),
                    expression = listOf(expression.toString(), operation.symbol.label),
                    lastOperator = operation
                )

                // Act:
                val newState = engine.handleBinary(initialState, operation)

                newState.also { println(it) }

                // Assert:
                newState.lastOperand shouldBe ""
                newState.lastResult shouldBe expected.toString()
                newState.expression shouldBe listOf("$expected", operation.symbol.label)
                newState.lastOperator shouldBe operation
            }

            @ParameterizedTest
            @CsvSource(
                "'NaN', 5", // Invalid expression (NaN)
            )
            fun `should return same state if previousNumber is not a valid number`(
                expression: String,
                lastOperand: String,
            ) {
                // Arrange:
                val operandRightOrNaN = expression.toDoubleOrNull() ?: Double.NaN
                val initialState = state.copy(
                    lastOperand = lastOperand,
                    expression = listOf(operandRightOrNaN.toString()),
                    lastOperator = binary
                )

                // Act:
                val newState = engine.handleBinary(initialState, binary)

                // Assert:
                newState shouldBe initialState
            }

            @ParameterizedTest
            @CsvSource(
                "5, 'NaN'",  // Invalid lastOperand (NaN)
            )
            fun `should return same state if lastOperand is not a valid number`(
                expression: String,
                lastOperand: String,
            ) {
                // Arrange:
                val operandRightOrNaN = lastOperand.toDoubleOrNull() ?: Double.NaN
                val initialState = state.copy(
                    lastOperand = operandRightOrNaN.toString(),
                    expression = listOf(expression),
                    lastOperator = binary
                )

                // Act:
                val newState = engine.handleBinary(initialState, binary)

                // Assert:
                newState shouldBe initialState
            }
        }

        @Nested
        inner class EnterBinary {

            private lateinit var binary: ButtonCalculatorBinary

            @BeforeEach
            fun setUp() {
                binary = ButtonCalculatorBinary.Multiplication
            }

            @Test
            fun `should set binary operation when there is no existing lastOperator`() {
                // Act:
                val newState = engine.handleBinary(state, binary)
                // Assert:
                binary shouldBe newState.lastOperator
            }

            @Test
            fun `should replace binary operation if one already exists`() {
                // Arrange:
                val initialState = state.copy(lastOperator = ButtonCalculatorBinary.Addition)
                // Act:
                val newState = engine.handleBinary(initialState, binary)
                // Assert
                binary shouldBe newState.lastOperator
            }

            @Test
            fun `should move lastOperand to expression and set binary lastOperator`() {
                // Arrange:
                val initialState = state.copy(lastOperand = "329")
                // Act:
                val newState = engine.handleBinary(initialState, binary)
                // Assert
                binary shouldBe newState.lastOperator
                initialState.expression.isEmpty() shouldBe newState.lastOperand.isEmpty()
            }

            @ParameterizedTest
            @CsvSource("'NaN'")
            fun `should return same state when trying to enter binary on NaN lastOperand`(
                lastOperand: String
            ) {
                // Arrange:
                val operandRightOrNaN = lastOperand.toDoubleOrNull() ?: Double.NaN
                val initialState = state.copy(lastOperator = binary, lastOperand = operandRightOrNaN.toString())

                // Act:
                val invalidState = engine.handleBinary(initialState,binary)

                // Assert:
                invalidState.lastOperand.toDouble().shouldBeNaN()
            }
        }
    }

    @Nested
    inner class HandleUnary {

        private lateinit var unary: ButtonCalculatorUnary

        @Nested
        inner class ApplySign {

            @BeforeEach
            fun setUp() {
                unary = ButtonCalculatorUnary.Sign
            }

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
                state = state.copy(lastOperand = number.toString())

                // Act:
                val newState = engine.handleUnary(state, unary)

                // Assert:
                newState.lastOperand shouldBe expectedResult.toString()
            }
        }

        @Nested
        inner class ApplyPercentage {

            @BeforeEach
            fun setUp() {
                unary = ButtonCalculatorUnary.Percentage
            }

            @ParameterizedTest
            @CsvSource(
                "500.0, 5.0",
                "5.0, 0.05",
                "0.0, 0.0",
                "-100, -1"
            )
            fun `should correctly apply percent operation`(number: Double, expectedResult: Double) {
                // Arrange:
                state = state.copy(lastOperand = number.toString())

                // Act:
                val newState = engine.handleUnary(state, unary)

                // Assert:
                newState.lastOperand shouldBe expectedResult.toString()
            }
        }
    }

    @Nested
    inner class HandleControl {

        private lateinit var control: ButtonCalculatorControl
        private lateinit var binary: ButtonCalculatorBinary

        @Nested
        inner class EnterDecimal {

            @BeforeEach
            fun setUp() {
                control = ButtonCalculatorControl.Decimal
                binary = ButtonCalculatorBinary.Multiplication
            }

            @Test
            fun `should add a decimal point if not already present`() {
                // Act:
                val newState = engine.handleControl(state, control)
                // Assert:
                newState.lastOperand.shouldContain(".")
            }

            @Test
            fun `should not add a decimal point if already present`() {
                // Arrange:
                val initialState = state.copy(lastOperand = "32.9")
                // Act:
                val newState = engine.handleControl(initialState, control)
                // Assert:
                newState.lastOperand.shouldNotMatch("32.9.")
            }

            @Test
            fun `should add a decimal point and zero if expression is not present`() {
                // Act:
                val newState = engine.handleControl(state, control)
                // Assert:
                newState.lastOperand.shouldContain("0.")
            }

            @ParameterizedTest
            @CsvSource(
                "23,'NaN'",
                "'NaN', 23"
            )
            fun `should return same state when trying to enter decimal on NaN lastOperand`(
                expression: String,
                lastOperand: String
            ) {
                // Arrange:
                val operandRightOrNaN = lastOperand.toDoubleOrNull()
                val operandLeftOrNaN = expression.toDoubleOrNull()
                val initialState = state.copy(expression = listOf(operandLeftOrNaN.toString()), lastOperator = binary, lastOperand = operandRightOrNaN.toString())

                // Act:
                val newState = engine.handleControl(initialState, control)

                // Assert:
                newState shouldBe initialState
            }
        }

        @Nested
        inner class ApplyClearAll {

            @Test
            fun `should reset calculator state to default`() {
                // Arrange:
                val button = ButtonCalculatorControl.AllClear

                val initialState = state.copy(
                    lastOperand = "32.9",
                    expression = listOf("123"),
                    lastOperator = ButtonCalculatorBinary.Multiplication
                )
                // Act:
                val newState = engine.handleControl(initialState, button)

                // Assert:
                newState.lastOperand shouldBe "0"
                newState.lastOperator shouldBe null
                newState.expression shouldBe emptyList()
                newState.activeButton shouldBe null
            }
        }

        @Nested
        inner class ApplyClear {

            @BeforeEach
            fun setUp() {
                control = ButtonCalculatorControl.Clear
                binary = ButtonCalculatorBinary.Multiplication
            }

            private fun provideRepeatableEqualsArguments(): Stream<Arguments> {
                return TestArgumentsEngineState.provideRepeatableEqualsArguments()
            }

            @Test
            fun `should clear operation and restore expression as lastOperand if lastOperator exists`() {
                // Arrange:
                val initialState = state.copy(
                    lastOperand = "329",
                    expression = listOf("329, ${ButtonCalculatorBinary.Multiplication.symbol.label}"),
                    lastOperator = ButtonCalculatorBinary.Multiplication
                )
                // Act:
                val newState = engine.handleControl(initialState, control)
                // Assert:
                "329" shouldBe newState.lastOperand
            }

            @Test
            fun `should clear lastOperand number to zero if expression is empty`() {
                // Arrange:
                val initialState = state.copy(
                    lastOperand = "329",
                    expression = emptyList(),
                    lastOperator = null
                )
                // Act:
                val newState = engine.handleControl(initialState, control)
                // Assert:
                "0" shouldBe newState.lastOperand
            }

            @ParameterizedTest
            @CsvSource("'NaN'")
            fun `should return default state when applying clear on NaN lastOperand`(
                lastOperand: String
            ) {
                // Arrange:
                val operandRightOrNaN = lastOperand.toDoubleOrNull() ?: Double.NaN
                val initialState = state.copy(expression = listOf(operandRightOrNaN.toString()), lastOperator = binary, lastOperand = operandRightOrNaN.toString())

                // Act:
                val stateCleared = engine.handleControl(initialState, control)

                // Assert:
                state shouldBe stateCleared
            }

            @ParameterizedTest
            @MethodSource("provideRepeatableEqualsArguments")
            fun `should return default state when applying clear on equal repeatable`(
                expression: Double,
                operation: ButtonCalculatorBinary,
                lastOperand: Double,
            ) {
                // Arrange:
                val equals = ButtonCalculatorControl.Equals

                val initialState = state.copy(
                    lastOperand = lastOperand.toString(),
                    expression = listOf(expression.toString(), operation.toString()),
                    lastOperator = operation
                )

                // Act & Assert:
                var stateEqual = initialState
                for (i in 1..10) { stateEqual = engine.handleControl(stateEqual, equals) }

                val stateCleared = engine.handleControl(stateEqual, control)

                // Assert:
                state.shouldBeEqualToIgnoringFields(stateCleared, CalculatorState::activeButton)
            }
        }

        @Nested
        inner class ApplyEquals {

            @BeforeEach
            fun setUp() {
                control = ButtonCalculatorControl.Equals
                binary = ButtonCalculatorBinary.Multiplication
            }

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
                lastOperand: Double,
                expected: Double,
            ) {
                // Arrange:
                val initialState = state.copy(
                    lastOperand = lastOperand.toString(),
                    expression = listOf(expression.toString(), operation.toString()),
                    lastOperator = operation,
                )

                // Act:
                val newState = engine.handleControl(initialState, control)

                // Assert:
                newState.lastOperand shouldBe expected.toString()
                newState.expression[0] shouldBe expected.toString()
                newState.lastOperator shouldBe initialState.lastOperator
            }

            @ParameterizedTest
            @CsvSource(
                "'NaN', 5",
                "5, 'NaN'",
            )
            fun `should return same state if lastOperand or expression are not a valid number`(
                expression: String,
                lastOperand: String,
            ) {
                // Arrange:
                val operandRightOrNaN = lastOperand.toDoubleOrNull() ?: Double.NaN
                val initialState = state.copy(
                    lastOperand = operandRightOrNaN.toString(),
                    expression = listOf(expression),
                )

                // Act:
                val newState = engine.handleControl(initialState, control)

                // Assert:
                newState.lastOperand shouldBe initialState.lastOperand
                newState.expression shouldBe initialState.expression
                newState.lastOperator shouldBe initialState.lastOperator
            }

            @ParameterizedTest
            @MethodSource("provideRepeatableEqualsArguments")
            fun `should perform repeatable equals correctly`(
                expression: Double,
                operation: ButtonCalculatorBinary,
                lastOperand: Double,
                expected: Double
            ) {
                // Arrange:
                val initialState = state.copy(
                    lastOperand = lastOperand.toString(),
                    expression = listOf(expression.toString(), operation.toString()),
                    lastOperator = operation
                )

                // Act & Assert:
                var state = initialState
                for (i in 1..10) { state = engine.handleControl(state, control) }

                state.also { println(it) }

                // Assert:
                state.lastOperand.toDouble().shouldBe(expected plusOrMinus (1e-9))
//            state.expression[0].toDouble().shouldBe(expected plusOrMinus (1e-9))
//            state.lastResult?.toDouble().shouldBe(lastOperand plusOrMinus (1e-9))
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
                lastOperand: String,
                expectedNumber: String,
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = listOf(expression, ButtonCalculatorBinary.Addition.toString()),
                    lastOperator = ButtonCalculatorBinary.Addition,
                    lastOperand = lastOperand,
                )
                val unary = ButtonCalculatorUnary.Percentage

                // Act
                val newState = engine.handleUnary(initialState, unary)

                newState.also { println(it) }

                val resultState = engine.handleControl(newState, control)

                // Assert:
                expectedNumber shouldBe resultState.lastOperand
            }
        }
    }

    @Nested
    inner class HandleNumber {

        private lateinit var binary: ButtonCalculatorBinary
        private lateinit var number1: ButtonCalculatorNumber
        private lateinit var number2: ButtonCalculatorNumber

        @BeforeEach
        fun setUp() {
            number1 = ButtonCalculatorNumber.Nine
            number2 = ButtonCalculatorNumber.Three
            binary = ButtonCalculatorBinary.Multiplication
        }
        @Nested
        inner class EnterNumber {

            @Test
            fun `should add a number to an empty lastOperand`() {
                // Act:
                val newState = engine.handleNumber(state, number1)
                // Assert:
                number1.symbol.label.toInt() shouldBeEqual newState.lastOperand.toInt()
            }

            @Test
            fun `should append a number to the lastOperand`() {
                // Arrange:
                val initialState = state.copy(lastOperand = number1.symbol.label)
                // Act:
                val newState = engine.handleNumber(initialState, number2)
                // Assert:
                (number1.symbol.label + number2.symbol.label) shouldBeEqual newState.lastOperand
            }

            @Test
            fun `should not add a number if max length is reached`() {
                // Arrange:
                val initialState = state.copy(lastOperand = "1".repeat(MAX_NUM_LENGTH))
                // Act:
                val newState = engine.handleNumber(initialState, number1)
                // Assert:
                1111111111 shouldBeEqual newState.lastOperand.toInt()
            }

            @ParameterizedTest
            @CsvSource(
                "23,'NaN'",
            )
            fun `should return same state when trying to enter number on NaN lastOperand`(
                expression: String,
                lastOperand: String
            ) {
                // Arrange:
                val operandRightOrNaN = lastOperand.toDoubleOrNull() ?: Double.NaN
                val initialState = state.copy(expression = listOf(expression), lastOperator = binary, lastOperand = operandRightOrNaN.toString())

                // Act:
                val newState = engine.handleNumber(initialState,number1)

                // Assert:
                initialState shouldBe newState
            }


            @ParameterizedTest
            @CsvSource(
                "'NaN', 23",
            )
            fun `should return same state when trying to enter number on NaN expression`(
                expression: String,
                lastOperand: String
            ) {
                // Arrange:
                val operandLeftOrNaN = lastOperand.toDoubleOrNull() ?: Double.NaN
                val initialState = state.copy(expression = listOf(operandLeftOrNaN.toString()), lastOperator = binary, lastOperand = lastOperand)

                // Act:
                val newState = engine.handleNumber(initialState,number1)

                // Assert:
                initialState.shouldBeEqualToIgnoringFields(newState, CalculatorState::lastOperand)
            }
        }
    }
}