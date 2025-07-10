package com.example.calculatorApp.domain

import com.example.calculatorApp.testData.TestDataEngineStateStandard
import com.example.calculatorApp.arguments.TestArgumentsEngineState
import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.ParserToken
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.Token.Companion.firstNumberOrNull
import com.example.calculatorApp.domain.ast.TokenizerUtils.toBinaryOperator
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.state.CalculatorState
import com.example.calculatorApp.utils.Constants.MAX_NUM_LENGTH
import com.example.calculatorApp.model.symbols.SymbolButtonUtils.toButton
import io.kotest.matchers.comparables.shouldBeGreaterThanOrEqualTo
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.equality.shouldBeEqualToIgnoringFields
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldMatch
import io.kotest.matchers.string.shouldNotContain
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.pow
import kotlin.streams.asStream

class EngineStateStandardTest {

    private lateinit var state: CalculatorState
    private lateinit var parser: ParserToken
    private lateinit var engineMath: EngineMath
    private lateinit var engineNode: EngineNode
    private lateinit var engineState: EngineState

    @BeforeEach
    fun setUp() {
        // Arrange:
        state = CalculatorState()
        parser = ParserToken()
        engineMath = EngineMathStandard()
        engineNode = EngineNodeStandard(engineMath)
        engineState = EngineStateStandard(engineMath, engineNode, parser)
    }

    @Nested
    inner class HandleBinary {
        // Arrange:
        private fun provideArguments(): Stream<TestDataEngineStateStandard> =
            TestArgumentsEngineState.provideStateBinary().asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should return the same state if hasError is true`(
            testData: TestDataEngineStateStandard
        ) {
            // Arrange:
            val initialState = state.copy(
                lastOperand = testData.lastOperand,
                hasError = true,
            )
            // Act:
            val newState = engineState.handleBinary(
                initialState,
                testData.buttonBinary as ButtonCalculatorBinary,
            )
            // Assert:
            newState shouldBe initialState
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should update the state if hasError is false`(
            testData: TestDataEngineStateStandard
        ) {
            // Arrange:
            val initialState = state.copy(
                lastOperand = testData.lastOperand,
                hasError = false,
            )
            // Act:
            val newState = engineState.handleBinary(
                initialState,
                testData.buttonBinary as ButtonCalculatorBinary,
            )
            // Assert:
            newState shouldNotBe initialState
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should apply correct binary operator when hasError is false`(
            testData: TestDataEngineStateStandard
        ) {
            // Arrange:
            val initialState = state.copy(
                lastOperand = testData.lastOperand,
                hasError = false,
            )
            // Act:
            val newState = engineState.handleBinary(
                initialState,
                testData.buttonBinary as ButtonCalculatorBinary,
            )
            // Assert:
            newState.lastOperator shouldBe testData.buttonBinary.toBinaryOperator()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should add lastOperand and binary operation to expression when applying binary operator`(
            testData: TestDataEngineStateStandard
        ) {
            // Arrange:
            val initialState = state.copy(
                lastOperand = testData.lastOperand,
                hasError = false,
            )
            // Act:
            val newState = engineState.handleBinary(
                initialState,
                testData.buttonBinary as ButtonCalculatorBinary,
            )
            // Assert:
            newState.expression shouldBe testData.expression
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should replace last binary operator when a new one is entered`(
            testData: TestDataEngineStateStandard
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = listOf(
                    Token.Number(testData.lastOperand.toDouble()),
                    Token.Binary(OperatorBinary.Addition),
                ),
                lastOperand = "",
                hasError = false,
            )
            // Act:
            val newState = engineState.handleBinary(
                initialState,
                testData.buttonBinary as ButtonCalculatorBinary,
            )
            // Assert:
            newState.expression shouldBe testData.expression
        }
    }

    @Nested
    inner class HandleUnary {
        // Arrange:
        private fun provideArgumentsSign(): Stream<TestDataEngineStateStandard> =
            TestArgumentsEngineState.provideStateUnary().asStream()
                .filter { it.buttonUnary is ButtonCalculatorUnary.Sign }

        // Arrange:
        private fun provideArgumentsPercentage(): Stream<TestDataEngineStateStandard> =
            TestArgumentsEngineState.provideStateUnary().asStream()
                .filter { it.buttonUnary is ButtonCalculatorUnary.Percentage }


        @ParameterizedTest
        @MethodSource("provideArgumentsSign")
        fun `should correctly apply sign operation`(
            testData: TestDataEngineStateStandard
        ) {
            // Arrange:
            val initialState = state.copy(lastOperand = testData.lastOperand)
            // Act:
            val newState = engineState.handleUnary(
                initialState,
                testData.buttonUnary as ButtonCalculatorUnary.Sign,
            )
            // Assert:
            newState.lastOperand shouldBe testData.expectedSign()[testData.lastOperand]
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsPercentage")
        fun `should correctly apply percent operation`(
            testData: TestDataEngineStateStandard
        ) {
            // Arrange:
            val initialState = state.copy(
                expression = listOf(
                    Token.Number(testData.lastOperand.toDouble() * 2),
                    Token.Binary(testData.buttonBinary.toOperator() as OperatorBinary),
                ),
                lastOperand = testData.lastOperand,
                lastOperator = testData.buttonBinary.toOperator() as OperatorBinary,
            )
            // Act:
            val newState = engineState.handleUnary(
                initialState,
                testData.buttonUnary as ButtonCalculatorUnary.Percentage,
            )
            // Assert:
            newState.lastOperand shouldBe testData.expectedPercentage()[testData.lastOperand to testData.buttonBinary]
        }
    }

    @Nested
    inner class HandleControl {

        @Nested
        inner class ApplyClearAll {
            private fun provideArguments(): Stream<TestDataEngineStateStandard> =
                TestArgumentsEngineState.provideStateControl().asStream()
                    .filter { ButtonCalculatorControl.AllClear in it.buttonControls }

            @ParameterizedTest
            @MethodSource("provideArguments")
            fun `should reset calculator state to default`(
                testData: TestDataEngineStateStandard
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.expression,
                    lastOperand = testData.lastOperand,
                    lastOperator = testData.buttonBinary.toOperator() as OperatorBinary,
                    activeButton = testData.lastOperand.takeLast(1).toButton(),
                )
                // Act:
                val newState = engineState.handleControl(initialState, testData.buttonControls.filterIsInstance<ButtonCalculatorControl.AllClear>().firstOrNull()
                    ?: throw IllegalArgumentException("Expected at least one ClearAll button in buttonControls."))
                // Assert:
                newState shouldBe state
            }
        }

        @Nested
        inner class ApplyClear {

            private fun provideArguments(): Stream<TestDataEngineStateStandard> =
                TestArgumentsEngineState.provideStateControl().asStream()
                    .filter { ButtonCalculatorControl.Clear in it.buttonControls }

            @ParameterizedTest
            @MethodSource("provideArguments")
            fun `should clear lastOperand number to zero`(
                testData: TestDataEngineStateStandard
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.expression,
                    lastOperand = testData.lastOperand,
                    lastOperator = testData.buttonBinary.toOperator() as OperatorBinary,
                    activeButton = testData.lastOperand.takeLast(1).toButton(),
                )
                // Act:
                val newState = engineState.handleControl(
                    initialState, testData.buttonControls.filterIsInstance<ButtonCalculatorControl.Clear>().firstOrNull()
                    ?: throw IllegalArgumentException("Expected at least one Clear button in buttonControls.")
                )
                // Assert:
                newState.lastOperand shouldBe "0"
            }

            @ParameterizedTest
            @MethodSource("provideArguments")
            fun `should keep expression if is not empty`(
                testData: TestDataEngineStateStandard
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.expression,
                    lastOperand = testData.lastOperand,
                    lastOperator = testData.buttonBinary.toOperator() as OperatorBinary,
                    activeButton = testData.lastOperand.takeLast(1).toButton(),
                )
                // Act:
                val newState = engineState.handleControl(
                    initialState, testData.buttonControls.filterIsInstance<ButtonCalculatorControl.Clear>().firstOrNull()
                        ?: throw IllegalArgumentException("Expected at least one Clear button in buttonControls.")
                )
                // Assert:
                newState.expression shouldBe testData.expression
            }

            @ParameterizedTest
            @MethodSource("provideArguments")
            fun `should return default state when applying clear on state with error`(
                testData: TestDataEngineStateStandard
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.expression,
                    lastOperand = testData.lastOperand,
                    lastOperator = testData.buttonBinary.toOperator() as OperatorBinary,
                    activeButton = testData.lastOperand.takeLast(1).toButton(),
                    hasError = true,
                    errorMessage = "Error"
                )
                // Act:
                val newState = engineState.handleControl(
                    initialState,
                    testData.buttonControls.filterIsInstance<ButtonCalculatorControl.Clear>().firstOrNull()
                        ?: throw IllegalArgumentException("Expected at least one Clear button in buttonControls.")
                )
                // Assert:
                newState shouldBe state
            }

            @ParameterizedTest
            @MethodSource("provideArguments")
            fun `should return default state when applying clear on equal repeatable`(
                testData: TestDataEngineStateStandard
            ) {
                // Arrange: Initial state simulates for instance -2.5 + (-2.5)
                val initialState = state.copy(
                    expression = testData.expression,
                    lastOperand = testData.lastOperand,
                    lastOperator = testData.buttonBinary.toOperator() as OperatorBinary,
                    activeButton = testData.lastOperand.takeLast(1).toButton(),
                )

                // Act: User presses "=" three times
                var stateEqual = initialState

                repeat(3) { stateEqual = engineState.handleControl(
                    stateEqual, testData.buttonControls.filterIsInstance<ButtonCalculatorControl.Equals>().firstOrNull()
                        ?: throw IllegalArgumentException("Expected at least one Equals button in buttonControls.")
                    )
                }

                val newState = engineState.handleControl(
                    stateEqual,
                    testData.buttonControls.filterIsInstance<ButtonCalculatorControl.Clear>().firstOrNull()
                        ?: throw IllegalArgumentException("Expected at least one clear button in buttonControls.")
                )
                // Assert: Result should reflect Clear action, active button is Clear
                newState.shouldBeEqualToIgnoringFields(state, CalculatorState::activeButton)
            }
        }

        @Nested
        inner class EnterDecimal {
            // Arrange:
            private fun provideArguments(): Stream<TestDataEngineStateStandard> =
                TestArgumentsEngineState.provideStateControl().asStream()
                    .filter { ButtonCalculatorControl.Decimal in it.buttonControls }

            private fun provideArgumentsWithZeroOperand(): Stream<TestDataEngineStateStandard> =
                TestArgumentsEngineState.provideStateControl().asStream()
                    .filter { ButtonCalculatorControl.Decimal in it.buttonControls && it.lastOperand == "0.0" }

            private fun provideArgumentsWithNaNOperand(): Stream<TestDataEngineStateStandard> =
                TestArgumentsEngineState.provideStateControl().asStream()
                    .filter { ButtonCalculatorControl.Decimal in it.buttonControls && it.lastOperand == "NaN" }

            @ParameterizedTest
            @MethodSource("provideArguments")
            fun `should add a decimal point if not already present`(
                testData: TestDataEngineStateStandard
            ) {
                // Arrange:
                val initialState = state.copy(lastOperand = testData.lastOperand)
                // Act:
                val newState = engineState.handleControl(
                    initialState,
                    testData.buttonControls.filterIsInstance<ButtonCalculatorControl.Decimal>().firstOrNull()
                        ?: throw IllegalArgumentException("Expected at least one Decimal button in buttonControls")
                )
                // Assert:
                if (newState.lastOperand == "NaN") newState.lastOperand.shouldNotContain(".") else newState.lastOperand.shouldContain(".")
            }

            @ParameterizedTest
            @MethodSource("provideArguments")
            fun `should not add a decimal point if already present`(
                testData: TestDataEngineStateStandard
            ) {
                // Arrange:
                val initialState = state.copy(lastOperand = testData.lastOperand)
                // Act:
                val newState = engineState.handleControl(
                    initialState,
                    testData.buttonControls.filterIsInstance<ButtonCalculatorControl.Decimal>().firstOrNull()
                        ?: throw IllegalArgumentException("Expected at least one Decimal button in buttonControls")
                )
                // Assert:
                newState.lastOperand
                    .takeIf { it == "NaN" }
                    ?.let { it.count { c -> c == '.' } shouldBe 0 }
                    ?: (newState.lastOperand.count { it == '.' } shouldBe 1)
            }

            @ParameterizedTest
            @MethodSource("provideArgumentsWithZeroOperand")
            fun `should add a decimal point and zero if operand is zero`(
                testData: TestDataEngineStateStandard
            ) {
                // Arrange:
                val initialState =
                    state.copy(lastOperand = if (testData.lastOperand == "0.0") "0" else testData.lastOperand)
                // Act:
                val newState = engineState.handleControl(
                    initialState,
                    testData.buttonControls.filterIsInstance<ButtonCalculatorControl.Decimal>().firstOrNull()
                        ?: throw IllegalArgumentException("Expected at least one Decimal button in buttonControls")
                )
                // Assert:
                newState.lastOperand.shouldMatch("0.")
            }

            @ParameterizedTest
            @MethodSource("provideArgumentsWithNaNOperand")
            fun `should return same state when trying to enter decimal on NaN lastOperand`(
                testData: TestDataEngineStateStandard
            ) {
                // Arrange:
                val initialState = state.copy(lastOperand = testData.lastOperand)
                // Act:
                val newState = engineState.handleControl(
                    initialState,
                    testData.buttonControls.filterIsInstance<ButtonCalculatorControl.Decimal>().firstOrNull()
                        ?: throw IllegalArgumentException("Expected at least one Decimal button in buttonControls")
                )
                // Assert:
                newState shouldBe initialState
                newState.lastOperand.shouldMatch("NaN")
            }
        }

        @Nested
        inner class ApplyEquals {
            private fun provideArguments(): Stream<TestDataEngineStateStandard> =
                TestArgumentsEngineState.provideStateControl().asStream()
                    .filter { ButtonCalculatorControl.Equals in it.buttonControls }

            @ParameterizedTest
            @MethodSource("provideArguments")
            fun `should correctly apply equals operation`(
                testData: TestDataEngineStateStandard
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.expression,
                    lastOperand = testData.lastOperand,
                    lastOperator = testData.buttonBinary.toOperator() as OperatorBinary,
                    activeButton = testData.lastOperand.takeLast(1).toButton(),
                )
                // Act:
                val newState = engineState.handleControl(
                    initialState, testData.buttonControls.filterIsInstance<ButtonCalculatorControl.Equals>().firstOrNull()
                    ?: throw IllegalArgumentException("Expected at least one Equals button in buttonControls.")
                )
                // Assert:
                newState.expression shouldBe when {
                    testData.lastOperand == "NaN" -> {
                        emptyList()
                    }
                    testData.buttonBinary is ButtonCalculatorBinary.Division && testData.lastOperand != "0.0" -> {
                        listOf(
                            Token.Number(testData.lastOperand.toDouble() / testData.lastOperand.toDouble()),
                            Token.Binary(testData.buttonBinary.toOperator() as OperatorBinary)
                        )
                    }
                    testData.buttonBinary is ButtonCalculatorBinary.Addition -> {
                        listOf(
                            Token.Number(testData.lastOperand.toDouble() + testData.lastOperand.toDouble()),
                            Token.Binary(testData.buttonBinary.toOperator() as OperatorBinary)
                        )
                    }
                    else -> emptyList()
                }
                newState.lastOperand shouldBe ""
                newState.lastResult shouldBe null
                newState.lastOperator shouldBe testData.buttonBinary.toOperator() as OperatorBinary
                newState.cachedOperand shouldBe when {
                    testData.lastOperand == "NaN" -> null
                    testData.lastOperand == "0.0" && testData.buttonBinary is ButtonCalculatorBinary.Division -> null
                    else -> testData.lastOperand.toDouble().toString()
                }
            }

            @ParameterizedTest
            @MethodSource("provideArguments")
            fun `should return state with error message if lastOperand or expression are not a valid`(
                testData: TestDataEngineStateStandard
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.expression,
                    lastOperand = testData.lastOperand,
                    lastOperator = testData.buttonBinary.toOperator() as OperatorBinary,
                    activeButton = testData.lastOperand.takeLast(1).toButton(),
                )
                // Act:
                val newState = engineState.handleControl(initialState, testData.buttonControls.filterIsInstance<ButtonCalculatorControl.Equals>().firstOrNull()
                    ?: throw IllegalArgumentException("Expected at least one Equals button in buttonControls.")
                )
                // Assert:
                val expected = when {
                    testData.lastOperand == "NaN" || (testData.lastOperand == "0.0" && testData.buttonBinary is ButtonCalculatorBinary.Division) -> {
                        true to "Error"
                    }
                    else -> false to null
                }

                newState.hasError shouldBe expected.first
                newState.errorMessage shouldBe expected.second
            }

            @ParameterizedTest
            @MethodSource("provideArguments")
            fun `should perform repeatable equals with valid list of expressions`(
                testData: TestDataEngineStateStandard
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.expression,
                    lastOperand = testData.lastOperand,
                    lastOperator = testData.buttonBinary.toOperator() as OperatorBinary,
                    activeButton = testData.lastOperand.takeLast(1).toButton(),
                )
                // Act: User presses "=" ten times
                var stateEqual = initialState

                repeat(10) { stateEqual = engineState.handleControl(
                    stateEqual, testData.buttonControls.filterIsInstance<ButtonCalculatorControl.Equals>().firstOrNull()
                        ?: throw IllegalArgumentException("Expected at least one Equals button in buttonControls.")
                    )
                }
                val newState = stateEqual
                // Assert:
                val expected = when {
                    testData.lastOperand == "NaN" -> {
                        null
                    }
                    testData.buttonBinary is ButtonCalculatorBinary.Division && testData.lastOperand != "0.0" -> {
                        testData.lastOperand.toDouble() / (testData.lastOperand.toDouble().pow(10 + 1)) * testData.lastOperand.toDouble()
                    }
                    testData.buttonBinary is ButtonCalculatorBinary.Addition -> {
                        testData.lastOperand.toDouble() * (10 + 1)
                    }
                    else -> null
                }

                newState.expression.firstNumberOrNull()?.value.shouldBe(expected?.plusOrMinus((1e-9)))
                    ?: (newState.expression.firstNumberOrNull() shouldBe null)
            }

            @ParameterizedTest
            @MethodSource("provideArguments")
            fun `should repeat operation using first operand when second is missing`(
                testData: TestDataEngineStateStandard
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.expression,
                    lastOperand = "",
                    lastOperator = testData.buttonBinary.toOperator() as OperatorBinary,
                    activeButton = testData.lastOperand.takeLast(1).toButton(),
                )
                // Act: User presses "=" ten times
                var stateEqual = initialState

                repeat(10) { stateEqual = engineState.handleControl(
                    stateEqual, testData.buttonControls.filterIsInstance<ButtonCalculatorControl.Equals>().firstOrNull()
                        ?: throw IllegalArgumentException("Expected at least one Equals button in buttonControls.")
                    )
                }
                val newState = stateEqual
                // Assert:
                val expected = when {
                    testData.lastOperand == "NaN" -> {
                        null
                    }
                    testData.buttonBinary is ButtonCalculatorBinary.Division && testData.lastOperand != "0.0" -> {
                        testData.lastOperand.toDouble() / (testData.lastOperand.toDouble().pow(10 + 1)) * testData.lastOperand.toDouble()
                    }
                    testData.buttonBinary is ButtonCalculatorBinary.Addition -> {
                        testData.lastOperand.toDouble() * (10 + 1)
                    }
                    else -> null
                }

                newState.expression.firstNumberOrNull()?.value?.shouldBe(expected?.plusOrMinus(1e-9))
                    ?: (newState.expression.firstNumberOrNull() shouldBe null)
            }

            @ParameterizedTest
            @MethodSource("provideArguments")
            fun `should correctly apply equals operation after percent operation is applied`(
                testData: TestDataEngineStateStandard,
            ) {
                // Arrange:
                val initialState = state.copy(
                    expression = testData.expression,
                    lastOperand = (testData.lastOperand.toDouble() + 10.0).toString(),
                    lastOperator = testData.buttonBinary.toOperator() as OperatorBinary,
                    activeButton = testData.lastOperand.takeLast(1).toButton(),
                )
                // Act:
                val statePercentage = engineState.handleUnary(initialState, testData.buttonUnary as ButtonCalculatorUnary.Percentage)

                val newState = engineState.handleControl(statePercentage, testData.buttonControls.filterIsInstance<ButtonCalculatorControl.Equals>().firstOrNull()
                    ?: throw IllegalArgumentException("Expected at least one Equals button in buttonControls.")
                )
                // Assert:
                newState.expression.firstNumberOrNull()?.value.toString() shouldBe testData.expectedPercentageWithEquals()[testData.lastOperand to testData.buttonBinary]
            }
        }
    }

    @Nested
    inner class HandleNumber {
        // Arrange:
        private fun provideArguments(): Stream<TestDataEngineStateStandard> =
            TestArgumentsEngineState.provideStateNumber().asStream()

        /**
         * Lightweight test argument provider used in cases where the full set of test data
         * isn't needed or would add unnecessary overhead.
         */
        private fun provideArgumentsNaNLastOperand(): Stream<TestDataEngineStateStandard> =
            TestArgumentsEngineState.provideStateNumber().asStream().filter { it.lastOperand == "NaN" }

        @ParameterizedTest
        @MethodSource("provideArgumentsNaNLastOperand")
        fun `should add a number to an empty lastOperand`(
            testData: TestDataEngineStateStandard
        ) {
            // Arrange:
            val initialState = state.copy(lastOperand = "")
            // Act:
            val newState = engineState.handleNumber(
                initialState,
                testData.buttonNumber as ButtonCalculatorNumber,
            )
            // Assert:
            testData.buttonNumber.symbol.label shouldBeEqual newState.lastOperand
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should append a number to the lastOperand`(
            testData: TestDataEngineStateStandard
        ) {
            // Arrange:
            val initialState = state.copy(lastOperand = testData.lastOperand)
            // Act:
            val newState = engineState.handleNumber(
                initialState,
                testData.buttonNumber as ButtonCalculatorNumber,
            )
            // Assert:
            testData.expectedNumber()[testData.lastOperand] shouldBe newState.lastOperand
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsNaNLastOperand")
        fun `should not add a number if max length is reached`(
            testData: TestDataEngineStateStandard
        ) {
            // Arrange:
            val initialState = state.copy(lastOperand = "1".repeat(MAX_NUM_LENGTH))
            // Act:
            val newState = engineState.handleNumber(
                initialState,
                testData.buttonNumber as ButtonCalculatorNumber,
            )
            // Assert:
            newState.also { println(it) }
            "1111111111" shouldBeEqual newState.lastOperand
            ("1111111111" + testData.lastOperand).length shouldBeGreaterThanOrEqualTo MAX_NUM_LENGTH
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsNaNLastOperand")
        fun `should return same state when trying to enter number on NaN lastOperand`(
            testData: TestDataEngineStateStandard
        ) {
            // Arrange:
            val initialState = state.copy(lastOperand = testData.lastOperand)
            // Act:
            val newState = engineState.handleNumber(
                initialState,
                testData.buttonNumber as ButtonCalculatorNumber,
            )
            // Assert:
            testData.expectedNumber()[testData.lastOperand] shouldBe newState.lastOperand
        }

        @Disabled("This test is disabled because the app now resets state on NaN, making this scenario obsolete.")
        @Test
        fun `should return same state when trying to enter number on NaN expression`() {
            // Arrange:
            // Act:
            // Assert:
        }
    }
}