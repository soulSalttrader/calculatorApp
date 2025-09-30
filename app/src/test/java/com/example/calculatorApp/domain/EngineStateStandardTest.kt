package com.example.calculatorApp.domain

import com.example.calculatorApp.arguments.TestArgumentsEngineState.provideEngineStateBinaryTestCases
import com.example.calculatorApp.arguments.TestArgumentsEngineState.provideEngineStateControlTestCases
import com.example.calculatorApp.arguments.TestArgumentsEngineState.provideEngineStateNumberTestCases
import com.example.calculatorApp.arguments.TestArgumentsEngineState.provideEngineStateUnaryTestCases
import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.ParserToken
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.Token.Companion.firstNumberOrNull
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.state.CalculatorStateDomain
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedEngineState
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputEngineState
import com.example.calculatorApp.testData.scenario.context.shouldMatch
import com.example.calculatorApp.testData.scenario.context.shouldMatchWithTolerance
import com.example.calculatorApp.testData.scenario.engineState.EngineState.Binary
import com.example.calculatorApp.testData.scenario.engineState.EngineState.Control
import com.example.calculatorApp.testData.scenario.engineState.EngineState.Numeric
import com.example.calculatorApp.testData.scenario.engineState.EngineState.Unary
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream

class EngineStateStandardTest {

    private lateinit var state: CalculatorStateDomain
    private lateinit var parser: ParserToken
    private lateinit var engineMath: EngineMath
    private lateinit var engineNode: EngineNode
    private lateinit var engineState: EngineState

    @BeforeEach
    fun setUp() {
        // Arrange:
        state = CalculatorStateDomain()
        parser = ParserToken()
        engineMath = EngineMathStandard()
        engineNode = EngineNodeStandard(engineMath)
        engineState = EngineStateStandard(engineMath, engineNode, parser)
    }

    @Nested
    inner class HandleBinary {
        // Arrange:
        private fun provideArgumentsHasError(): Stream<TestCase<Input, Expected>> =
            provideEngineStateBinaryTestCases(Binary.Error).take(5).asStream()

        private fun provideArgumentsUpdate(): Stream<TestCase<Input, Expected>> =
            provideEngineStateBinaryTestCases(Binary.Update).asStream()

        private fun provideArgumentsSuccess(): Stream<TestCase<Input, Expected>> =
            provideEngineStateBinaryTestCases(Binary.Success).asStream()

        private fun provideArgumentsReplace(): Stream<TestCase<Input, Expected>> =
            provideEngineStateBinaryTestCases(Binary.Replace).asStream()

        @ParameterizedTest
        @MethodSource("provideArgumentsHasError")
        fun `should return the same state if hasError is true`(
            testData: TestCase<InputEngineState.Binary, ExpectedEngineState.Binary>
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = testData.input.context.expression,
                lastOperand = testData.input.context.lastOperand,
                activeButton = testData.input.context.activeButton,
                lastOperator = testData.input.context.lastOperator,
                hasError = testData.input.context.hasError,
                errorMessage = testData.input.context.errorMessage,
            )
            // Act:
            val newState = engineState.handleBinary(
                initialState,
                ButtonCalculatorBinary.Multiplication,
            )
            // Assert:
            newState.hasError shouldBe testData.expected.context.hasError
            newState.errorMessage shouldBe testData.expected.context.errorMessage
            newState shouldMatch testData.expected.context
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsUpdate")
        fun `should update the state if hasError is false`(
            testData: TestCase<InputEngineState.Binary, ExpectedEngineState.Binary>
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = testData.input.context.expression,
                lastOperand = testData.input.context.lastOperand,
                activeButton = testData.input.context.activeButton,
                lastOperator = testData.input.context.lastOperator,
                hasError = testData.input.context.hasError,
                errorMessage = testData.input.context.errorMessage,
            )
            // Act:
            val newState = engineState.handleBinary(
                initialState,
                ButtonCalculatorBinary.Multiplication,
            )
            // Assert:
            newState shouldNotBe initialState
            newState shouldMatch testData.expected.context
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsSuccess")
        fun `should apply correct binary operator when hasError is false`(
            testData: TestCase<InputEngineState.Binary, ExpectedEngineState.Binary>
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = testData.input.context.expression,
                lastOperand = testData.input.context.lastOperand,
                activeButton = testData.input.context.activeButton,
                lastOperator = testData.input.context.lastOperator,
                hasError = testData.input.context.hasError,
                errorMessage = testData.input.context.errorMessage,
            )
            // Act:
            val newState = engineState.handleBinary(
                initialState,
                ButtonCalculatorBinary.Multiplication,
            )
            // Assert:
            newState shouldMatch testData.expected.context
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsSuccess")
        fun `should add lastOperand and binary operation to expression when applying binary operator`(
            testData: TestCase<InputEngineState.Binary, ExpectedEngineState.Binary>
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = testData.input.context.expression,
                lastOperand = testData.input.context.lastOperand,
                activeButton = testData.input.context.activeButton,
                lastOperator = testData.input.context.lastOperator,
                hasError = testData.input.context.hasError,
                errorMessage = testData.input.context.errorMessage,
            )
            // Act:
            val newState = engineState.handleBinary(
                initialState,
                ButtonCalculatorBinary.Multiplication,
            )
            // Assert:
            newState shouldMatch testData.expected.context
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsReplace")
        fun `should replace last binary operator when a new one is entered`(
            testData: TestCase<InputEngineState.Binary, ExpectedEngineState.Binary>
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = testData.input.context.expression,
                lastOperand = testData.input.context.lastOperand,
                activeButton = testData.input.context.activeButton,
                lastOperator = testData.input.context.lastOperator,
                hasError = testData.input.context.hasError,
                errorMessage = testData.input.context.errorMessage,
            )
            // Act:
            val newState = engineState.handleBinary(
                initialState,
                ButtonCalculatorBinary.Multiplication,
            )
            // Assert:
            newState shouldMatch testData.expected.context
        }
    }

    @Nested
    inner class HandleUnary {

        // Arrange:
        private fun provideArgumentsSign(): Stream<TestCase<Input, Expected>> =
            provideEngineStateUnaryTestCases(Unary.Sign).asStream()

        // Arrange:
        private fun provideArgumentsSignError(): Stream<TestCase<Input, Expected>> =
            provideEngineStateUnaryTestCases(Unary.SignError).asStream()

        // Arrange:
        private fun provideArgumentsPercentageOperand(): Stream<TestCase<Input, Expected>> =
            provideEngineStateUnaryTestCases(Unary.PercentageOperand).asStream()

        // Arrange:
        private fun provideArgumentsPercentageAddSub(): Stream<TestCase<Input, Expected>> =
            provideEngineStateUnaryTestCases(Unary.PercentageAddSub)
                .filter { isAddOrSubtract(it) }
                .asStream()

        private fun provideArgumentsPercentageMulDiv(): Stream<TestCase<Input, Expected>> =
            provideEngineStateUnaryTestCases(Unary.PercentageMulDiv)
                .filter { isMulOrDivision(it) }
                .asStream()

        // Arrange:
        private fun provideArgumentsPercentageError(): Stream<TestCase<Input, Expected>> =
            provideEngineStateUnaryTestCases(Unary.PercentageError).asStream()

        private fun isAddOrSubtract(case: TestCase<Input, Expected>): Boolean =
            (case.input as InputEngineState.Unary).context.lastOperator in
                    setOf(OperatorBinary.Addition, OperatorBinary.Subtraction)

        private fun isMulOrDivision(case: TestCase<Input, Expected>): Boolean =
            (case.input as InputEngineState.Unary).context.lastOperator in
                    setOf(OperatorBinary.Multiplication, OperatorBinary.Division)

        @ParameterizedTest
        @MethodSource("provideArgumentsSign")
        fun `should correctly apply sign operation`(
            testData: TestCase<InputEngineState.Unary, ExpectedEngineState.Unary>
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = testData.input.context.expression,
                lastOperand = testData.input.context.lastOperand,
                activeButton = testData.input.context.activeButton,
                lastOperator = testData.input.context.lastOperator,
                hasError = testData.input.context.hasError,
                errorMessage = testData.input.context.errorMessage,
            )
            // Act:
            val newState = engineState.handleUnary(
                initialState,
                ButtonCalculatorUnary.Sign,
            )
            // Assert:
            newState shouldMatch testData.expected.context
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsSignError")
        fun `should only update activeButton=Sign when state has error`(
            testData: TestCase<InputEngineState.Unary, ExpectedEngineState.Unary>
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = testData.input.context.expression,
                lastOperand = testData.input.context.lastOperand,
                activeButton = testData.input.context.activeButton,
                lastOperator = testData.input.context.lastOperator,
                hasError = testData.input.context.hasError,
                errorMessage = testData.input.context.errorMessage,
            )
            // Act:
            val newState = engineState.handleUnary(
                initialState,
                ButtonCalculatorUnary.Sign,
            )
            // Assert:
            testData.expected.context
            newState shouldMatch testData.expected.context
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsPercentageError")
        fun `should only update activeButton=Percentage when state has error`(
            testData: TestCase<InputEngineState.Unary, ExpectedEngineState.Unary>
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = testData.input.context.expression,
                lastOperand = testData.input.context.lastOperand,
                activeButton = testData.input.context.activeButton,
                lastOperator = testData.input.context.lastOperator,
                hasError = testData.input.context.hasError,
                errorMessage = testData.input.context.errorMessage,
            )
            // Act:
            val newState = engineState.handleUnary(
                initialState,
                ButtonCalculatorUnary.Percentage,
            )
            // Assert:
            newState shouldMatch testData.expected.context
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsPercentageOperand")
        fun `should correctly apply percent operation when only lastOperand is present`(
            testData: TestCase<InputEngineState.Unary, ExpectedEngineState.Unary>
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = testData.input.context.expression,
                lastOperand = testData.input.context.lastOperand,
                activeButton = testData.input.context.activeButton,
                lastOperator = testData.input.context.lastOperator,
                hasError = testData.input.context.hasError,
                errorMessage = testData.input.context.errorMessage,
            )
            // Act:
            val newState = engineState.handleUnary(
                initialState,
                ButtonCalculatorUnary.Percentage,
            )
            // Assert:
            newState shouldMatch testData.expected.context
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsPercentageAddSub")
        fun `should correctly apply percent operation in additive expressions (addition, subtraction)`(
            testData: TestCase<InputEngineState.Unary, ExpectedEngineState.Unary>
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = testData.input.context.expression,
                lastOperand = testData.input.context.lastOperand,
                activeButton = testData.input.context.activeButton,
                lastOperator = testData.input.context.lastOperator,
                hasError = testData.input.context.hasError,
                errorMessage = testData.input.context.errorMessage,
            )
            // Act:
            val newState = engineState.handleUnary(
                initialState,
                ButtonCalculatorUnary.Percentage,
            )
            // Assert:
            newState shouldMatch testData.expected.context
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsPercentageMulDiv")
        fun `should correctly apply percent operation in multiplicative expressions (multiplication, division)`(
            testData: TestCase<InputEngineState.Unary, ExpectedEngineState.Unary>
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = testData.input.context.expression,
                lastOperand = testData.input.context.lastOperand,
                activeButton = testData.input.context.activeButton,
                lastOperator = testData.input.context.lastOperator,
                hasError = testData.input.context.hasError,
                errorMessage = testData.input.context.errorMessage,
            )
            // Act:
            val newState = engineState.handleUnary(
                initialState,
                ButtonCalculatorUnary.Percentage,
            )
            // Assert:
            newState shouldMatch testData.expected.context
        }
    }

    @Nested
    inner class HandleControl {

        @Nested
        inner class ApplyClearAll {
            private fun provideArgumentsClearAll(): Stream<TestCase<Input, Expected>> =
                provideEngineStateControlTestCases(Control.ClearAll).asStream()

            @ParameterizedTest
            @MethodSource("provideArgumentsClearAll")
            fun `should reset calculator state to default`(
                testData: TestCase<InputEngineState.Control, ExpectedEngineState.Control>
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.input.context.expression,
                    lastOperand = testData.input.context.lastOperand,
                    activeButton = testData.input.context.activeButton,
                    lastOperator = testData.input.context.lastOperator,
                    hasError = testData.input.context.hasError,
                    errorMessage = testData.input.context.errorMessage,
                )
                // Act:
                val newState = engineState.handleControl(
                    initialState,
                    ButtonCalculatorControl.AllClear
                )
                // Assert:
                newState shouldMatch testData.expected.context
            }
        }

        @Nested
        inner class ApplyClear {

            private fun provideArgumentsClear(): Stream<TestCase<Input, Expected>> =
                provideEngineStateControlTestCases(Control.Clear).asStream()

            private fun provideArgumentsClearError(): Stream<TestCase<Input, Expected>> =
                provideEngineStateControlTestCases(Control.ClearError).asStream()

            @ParameterizedTest
            @MethodSource("provideArgumentsClear")
            fun `should clear lastOperand number to zero`(
                testData: TestCase<InputEngineState.Control, ExpectedEngineState.Control>
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.input.context.expression,
                    lastOperand = testData.input.context.lastOperand,
                    activeButton = testData.input.context.activeButton,
                    lastOperator = testData.input.context.lastOperator,
                    hasError = testData.input.context.hasError,
                    errorMessage = testData.input.context.errorMessage,
                )
                // Act:
                val newState = engineState.handleControl(
                    initialState,
                    ButtonCalculatorControl.Clear
                )
                // Assert:
                newState.lastOperand shouldBe "0"
                newState.lastOperand shouldBe SymbolButton.ZERO.label
                newState shouldMatch testData.expected.context
                testData.expected.context
            }

            @ParameterizedTest
            @MethodSource("provideArgumentsClear")
            fun `should clear lastOperand and keep expression if is not empty`(
                testData: TestCase<InputEngineState.Control, ExpectedEngineState.Control>
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.input.context.expression,
                    lastOperand = testData.input.context.lastOperand,
                    activeButton = testData.input.context.activeButton,
                    lastOperator = testData.input.context.lastOperator,
                    hasError = testData.input.context.hasError,
                    errorMessage = testData.input.context.errorMessage,
                )
                // Act:
                val newState = engineState.handleControl(
                    initialState,
                    ButtonCalculatorControl.Clear
                )
                // Assert:
                newState.expression shouldNotBe emptyList<Token>()
                newState.expression shouldBe initialState.expression
                newState shouldMatch testData.expected.context
            }

            @ParameterizedTest
            @MethodSource("provideArgumentsClearError")
            fun `should only update activeButton=Clear when state has error`(
                testData: TestCase<InputEngineState.Control, ExpectedEngineState.Control>
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.input.context.expression,
                    lastOperand = testData.input.context.lastOperand,
                    activeButton = testData.input.context.activeButton,
                    lastOperator = testData.input.context.lastOperator,
                    hasError = testData.input.context.hasError,
                    errorMessage = testData.input.context.errorMessage,
                )
                // Act:
                val newState = engineState.handleControl(
                    initialState,
                    ButtonCalculatorControl.Clear
                )
                // Assert:
                newState shouldMatch testData.expected.context
            }

            @ParameterizedTest
            @MethodSource("provideArgumentsClearError")
            fun `should only update activeButton=Clear when state has error on equal repeatable`(
                testData: TestCase<InputEngineState.Control, ExpectedEngineState.Control>
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.input.context.expression,
                    lastOperand = testData.input.context.lastOperand,
                    activeButton = testData.input.context.activeButton,
                    lastOperator = testData.input.context.lastOperator,
                    hasError = testData.input.context.hasError,
                    errorMessage = testData.input.context.errorMessage,
                )
                // Act: User presses "=" three times
                var stateEqual = initialState

                repeat(3) {
                    stateEqual = engineState.handleControl(
                        stateEqual,
                        ButtonCalculatorControl.Equals,
                    )
                }
                val newState = engineState.handleControl(
                    stateEqual,
                    ButtonCalculatorControl.Clear,
                )
                // Assert:
                newState shouldMatch testData.expected.context
            }
        }

        @Nested
        inner class EnterDecimal {
            // Arrange:
            private fun provideArgumentsDecimal(): Stream<TestCase<Input, Expected>> =
                provideEngineStateControlTestCases(Control.Decimal).asStream()

            // Arrange:
            private fun provideArgumentsDecimalZero(): Stream<TestCase<Input, Expected>> =
                provideEngineStateControlTestCases(Control.DecimalZero).asStream()

            // Arrange:
            private fun provideArgumentsDecimalError(): Stream<TestCase<Input, Expected>> =
                provideEngineStateControlTestCases(Control.DecimalError).asStream()

            @ParameterizedTest
            @MethodSource("provideArgumentsDecimal")
            fun `should add a decimal point if not already present`(
                testData: TestCase<InputEngineState.Control, ExpectedEngineState.Control>
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.input.context.expression,
                    lastOperand = testData.input.context.lastOperand,
                    activeButton = testData.input.context.activeButton,
                    lastOperator = testData.input.context.lastOperator,
                    hasError = testData.input.context.hasError,
                    errorMessage = testData.input.context.errorMessage,
                )
                // Act:
                val newState = engineState.handleControl(
                    initialState,
                    ButtonCalculatorControl.Decimal,
                )
                // Assert:
                newState shouldMatch testData.expected.context
            }

            @ParameterizedTest
            @MethodSource("provideArgumentsDecimal")
            fun `should not add a decimal point if already present`(
                testData: TestCase<InputEngineState.Control, ExpectedEngineState.Control>
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.input.context.expression,
                    lastOperand = testData.input.context.lastOperand,
                    activeButton = testData.input.context.activeButton,
                    lastOperator = testData.input.context.lastOperator,
                    hasError = testData.input.context.hasError,
                    errorMessage = testData.input.context.errorMessage,
                )
                // Act:
                val newState = engineState.handleControl(
                    initialState,
                    ButtonCalculatorControl.Decimal,
                )
                // Assert:
                newState shouldMatch testData.expected.context
            }

            @ParameterizedTest
            @MethodSource("provideArgumentsDecimalZero")
            fun `should add a decimal point and zero if operand is blank`(
                testData: TestCase<InputEngineState.Control, ExpectedEngineState.Control>
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.input.context.expression,
                    lastOperand = testData.input.context.lastOperand,
                    activeButton = testData.input.context.activeButton,
                    lastOperator = testData.input.context.lastOperator,
                    hasError = testData.input.context.hasError,
                    errorMessage = testData.input.context.errorMessage,
                )
                // Act:
                val newState = engineState.handleControl(
                    initialState,
                    ButtonCalculatorControl.Decimal,
                )
                // Assert:
                newState shouldMatch testData.expected.context
            }

            @ParameterizedTest
            @MethodSource("provideArgumentsDecimalError")
            fun `should return same state when trying to enter decimal on NaN lastOperand`(
                testData: TestCase<InputEngineState.Control, ExpectedEngineState.Control>
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.input.context.expression,
                    lastOperand = testData.input.context.lastOperand,
                    activeButton = testData.input.context.activeButton,
                    lastOperator = testData.input.context.lastOperator,
                    hasError = testData.input.context.hasError,
                    errorMessage = testData.input.context.errorMessage,
                )
                // Act:
                val newState = engineState.handleControl(
                    initialState,
                    ButtonCalculatorControl.Decimal,
                )
                // Assert:
                newState shouldMatch testData.expected.context
            }
        }
    }

        @Nested
        inner class ApplyEquals {
            // Arrange:
            private fun provideArgumentsEquals(): Stream<TestCase<Input, Expected>> =
                provideEngineStateControlTestCases(Control.Equals)
                    .filter { !isDivideByZero(it) }
                    .asStream()

            // Arrange:
            private fun provideArgumentsEqualsError(): Stream<TestCase<Input, Expected>> =
                provideEngineStateControlTestCases(Control.EqualsError)
                    .filter { isDivideByZero(it) }
                    .asStream()

            // Arrange:
            private fun provideArgumentsEqualsInvalid(): Stream<TestCase<Input, Expected>> =
                provideEngineStateControlTestCases(Control.EqualsInvalid)
                    .filter { !isDivideByZero(it) }
                    .asStream()

            // Arrange:
            private fun provideArgumentsEqualsRepeatable(): Stream<TestCase<Input, Expected>> =
                provideEngineStateControlTestCases(Control.EqualsRepeatable)
                    .filter { !isDivideByZero(it) }
                    .asStream()

            // Arrange:
            private fun provideArgumentsEqualsRepeatableOperand(): Stream<TestCase<Input, Expected>> =
                provideEngineStateControlTestCases(Control.EqualsRepeatableOperand)
                    .filter { !isDivideByZeroRepeatableMissing(it) }
                    .asStream()

            // Arrange:
            private fun provideArgumentsEqualsPercentage(): Stream<TestCase<Input, Expected>> =
                provideEngineStateControlTestCases(Control.EqualsPercentage)
                    .filter { !isDivideByZeroRepeatableMissing(it) && !isDivideByZero(it) }
                    .asStream()

            private fun isDivideByZero(case: TestCase<Input, Expected>): Boolean =
                (case.input as InputEngineState.Control).context.lastOperand.toDouble() == 0.0 &&
                        case.input.context.lastOperator == OperatorBinary.Division

            private fun isDivideByZeroRepeatableMissing(case: TestCase<Input, Expected>): Boolean =
                (case.input as InputEngineState.Control).context.expression.firstNumberOrNull()?.value == 0.0 &&
                        case.input.context.lastOperator == OperatorBinary.Division

            @ParameterizedTest
            @MethodSource("provideArgumentsEquals")
            fun `should correctly apply equals operation`(
                testData: TestCase<InputEngineState.Control, ExpectedEngineState.Control>
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.input.context.expression,
                    lastOperand = testData.input.context.lastOperand,
                    activeButton = testData.input.context.activeButton,
                    lastOperator = testData.input.context.lastOperator,
                    hasError = testData.input.context.hasError,
                    errorMessage = testData.input.context.errorMessage,
                )
                // Act:
                val newState = engineState.handleControl(
                    initialState,
                    ButtonCalculatorControl.Equals,
                )
                testData.expected.context
                // Assert:
                newState shouldMatch testData.expected.context
            }

            @ParameterizedTest
            @MethodSource("provideArgumentsEqualsInvalid")
            fun `should return state with error message if expression are not a valid`(
                testData: TestCase<InputEngineState.Control, ExpectedEngineState.Control>
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.input.context.expression,
                    lastOperand = testData.input.context.lastOperand,
                    activeButton = testData.input.context.activeButton,
                    lastOperator = testData.input.context.lastOperator,
                    hasError = testData.input.context.hasError,
                    errorMessage = testData.input.context.errorMessage,
                )
                // Act:
                val newState = engineState.handleControl(
                    initialState,
                    ButtonCalculatorControl.Equals,
                )
                // Assert:
                newState shouldMatch testData.expected.context
            }

            @ParameterizedTest
            @MethodSource("provideArgumentsEqualsRepeatable")
            fun `should perform repeatable equals with valid list of expressions`(
                testData: TestCase<InputEngineState.Control, ExpectedEngineState.Control>
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.input.context.expression,
                    lastOperand = testData.input.context.lastOperand,
                    activeButton = testData.input.context.activeButton,
                    lastOperator = testData.input.context.lastOperator,
                    hasError = testData.input.context.hasError,
                    errorMessage = testData.input.context.errorMessage,
                )
                // Act: User presses "=" ten times
                var stateEqual = initialState

                repeat(10) { stateEqual = engineState.handleControl(
                    stateEqual,
                    ButtonCalculatorControl.Equals,
                )

                }
                val newState = stateEqual
                // Assert:
                newState shouldMatchWithTolerance testData.expected.context
            }

            @ParameterizedTest
            @MethodSource("provideArgumentsEqualsRepeatableOperand")
            fun `should repeat operation using first operand when second is missing`(
                testData: TestCase<InputEngineState.Control, ExpectedEngineState.Control>
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.input.context.expression,
                    lastOperand = "",
                    activeButton = testData.input.context.activeButton,
                    lastOperator = testData.input.context.lastOperator,
                    hasError = testData.input.context.hasError,
                    errorMessage = testData.input.context.errorMessage,
                )
                // Act: User presses "=" ten times
                var stateEqual = initialState

                repeat(10) { stateEqual = engineState.handleControl(
                    stateEqual,
                    ButtonCalculatorControl.Equals,
                )
                }
                val newState = stateEqual
                // Assert:
                newState shouldMatchWithTolerance testData.expected.context
            }

            @ParameterizedTest
            @MethodSource("provideArgumentsEqualsPercentage")
            fun `should correctly apply equals operation after percent operation is applied`(
                testData: TestCase<InputEngineState.Control, ExpectedEngineState.Control>
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.input.context.expression,
                    lastOperand = testData.input.context.lastOperand,
                    activeButton = testData.input.context.activeButton,
                    lastOperator = testData.input.context.lastOperator,
                    hasError = testData.input.context.hasError,
                    errorMessage = testData.input.context.errorMessage,
                )
                // Act:
                val statePercentage = engineState.handleUnary(initialState, ButtonCalculatorUnary.Percentage)
                val newState = engineState.handleControl(statePercentage, ButtonCalculatorControl.Equals)
                // Assert:
                newState shouldMatchWithTolerance testData.expected.context
            }
        }

    @Nested
    inner class HandleNumber {
        // Arrange:
        private fun provideArgumentsSuccess(): Stream<TestCase<Input, Expected>> =
            provideEngineStateNumberTestCases(Numeric.Success).asStream()

        // Arrange:
        private fun provideArgumentsError(): Stream<TestCase<Input, Expected>> =
            provideEngineStateNumberTestCases(Numeric.Error).asStream()

        // Arrange:
        private fun provideArgumentsOperand(): Stream<TestCase<Input, Expected>> =
            provideEngineStateNumberTestCases(Numeric.Operand).asStream()

        // Arrange:
        private fun provideArgumentsMaxLength(): Stream<TestCase<Input, Expected>> =
            provideEngineStateNumberTestCases(Numeric.MaxLength).asStream()

        @ParameterizedTest
        @MethodSource("provideArgumentsOperand")
        fun `should add a number to an empty lastOperand`(
            testData: TestCase<InputEngineState.Number, ExpectedEngineState.Number>
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = testData.input.context.expression,
                lastOperand = testData.input.context.lastOperand,
                activeButton = testData.input.context.activeButton,
                lastOperator = testData.input.context.lastOperator,
                hasError = testData.input.context.hasError,
                errorMessage = testData.input.context.errorMessage,
            )
            // Act:
            val newState = engineState.handleNumber(
                initialState,
                ButtonCalculatorNumber.Nine,
            )
            // Assert:
            newState shouldMatch testData.expected.context
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsSuccess")
        fun `should append a number to the lastOperand`(
            testData: TestCase<InputEngineState.Number, ExpectedEngineState.Number>
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = testData.input.context.expression,
                lastOperand = testData.input.context.lastOperand,
                activeButton = testData.input.context.activeButton,
                lastOperator = testData.input.context.lastOperator,
                hasError = testData.input.context.hasError,
                errorMessage = testData.input.context.errorMessage,
            )
            // Act:
            val newState = engineState.handleNumber(
                initialState,
                ButtonCalculatorNumber.Nine,
            )
            // Assert:
            newState shouldMatch testData.expected.context
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsMaxLength")
        fun `should not add a number if max length is reached`(
            testData: TestCase<InputEngineState.Number, ExpectedEngineState.Number>
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = testData.input.context.expression,
                lastOperand = testData.input.context.lastOperand,
                activeButton = testData.input.context.activeButton,
                lastOperator = testData.input.context.lastOperator,
                hasError = testData.input.context.hasError,
                errorMessage = testData.input.context.errorMessage,
            )
            // Act:
            val newState = engineState.handleNumber(
                initialState,
                ButtonCalculatorNumber.Nine,
            )
            // Assert:
            newState shouldMatch testData.expected.context
        }

//        @Disabled("This test is disabled because the app now resets state on NaN, making this scenario obsolete.")
//        @Test
//        fun `should return same state when trying to enter number on NaN lastOperand`() {
//            // Arrange:
//            // Act:
//            // Assert:
//        }

//        @Disabled("This test is disabled because the app now resets state on NaN, making this scenario obsolete.")
//        @Test
//        fun `should return same state when trying to enter number on NaN expression`() {
//            // Arrange:
//            // Act:
//            // Assert:
//        }
    }
}