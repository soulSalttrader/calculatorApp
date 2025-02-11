package com.example.calculatorApp.model.state

import com.example.calculatorApp.arguments.TestArgumentsButton
import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.symbols.SymbolButton
import io.kotest.assertions.withClue
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CalculatorStateTest {

    private lateinit var state: CalculatorState

    @BeforeEach
    fun setUp() {
        // Arrange:
        state = CalculatorState(
            currentNumber = "0",
            operation  = null,
            previousNumber = "",
            activeButtonLabel = ""
        )
    }

    @Nested
    inner class ModifyWith {

        @Test
        fun `should apply first matching transformation`() {
            // Arrange:
            val modifiedState = state.copy(currentNumber = "5")

            // Act:
            val newState = modifiedState.modifyWith(
                { modifiedState.currentNumber == "5" } to { copy(operation = ButtonCalculatorArithmetic.Division) },
                { modifiedState.currentNumber == "10" } to { copy(currentNumber = "15") }
            )
            // Assert:
            ButtonCalculatorArithmetic.Division shouldBe newState.operation
        }

        @Test
        fun `should not modify state if no conditions match`() {
            // Arrange & Act:
            val newState = state.modifyWith(
                { state.currentNumber == "329" } to { copy(currentNumber = "200") }
            )

            // Assert:
            state.currentNumber shouldBeEqual newState.currentNumber
        }

        @Test
        fun `should apply only the first matching transformation`() {
            // Arrange & Act:
            val newState = state.modifyWith(
                { true } to { copy(currentNumber = "10") },
                { true } to { copy(currentNumber = "20") }
            )
            // Assert:
            "10" shouldBeEqual newState.currentNumber
        }
    }

    @Nested
    inner class CalculatorStatePropertiesTest {
        // Arrange: Setup test data (button instance)
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsButton.provideControlSymbols()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should update active button label correctly`(
            button: ButtonCalculatorControl,
            expectedSymbol: SymbolButton,
        ) {
            // Act:
            val modifiedState = state.copy(activeButtonLabel = button.symbol.label)

            // Assert:
            expectedSymbol.label shouldBe modifiedState.activeButtonLabel
        }

        @Test
        fun `should return the correct property for calculator state`() {

            // Arrange & Act & Assert:
            withClue("Expected currentNumber to be '0' by default.") {
                state.currentNumber shouldBe "0"
            }
            withClue("Expected previousNumber to be empty by default.") {
                state.previousNumber shouldBe ""
            }
            withClue("Expected operation to be null by default.") {
                state.operation shouldBe null
            }
            withClue("Expected activeButtonLabel to be empty by default.") {
                state.activeButtonLabel shouldBe ""
            }
        }

        @Test
        fun `should update current number correctly`() {
            // Arrange & Act:
            val modifiedState = state.copy(currentNumber = "5")

            // Assert:
            "5" shouldBe modifiedState.currentNumber
        }

        @Test
        fun `should update previous number correctly`() {
            // Arrange & Act
            val modifiedState = state.copy(previousNumber = "10")

            // Assert
            "10" shouldBe modifiedState.previousNumber
        }
    }
}