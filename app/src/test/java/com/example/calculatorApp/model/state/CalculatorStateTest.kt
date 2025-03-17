package com.example.calculatorApp.model.state

import com.example.calculatorApp.arguments.TestArgumentsButton
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
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
import kotlin.streams.asStream

class CalculatorStateTest {

    private lateinit var state: CalculatorState

    @BeforeEach
    fun setUp() {
        // Arrange:
        state = CalculatorState(
            expression = emptyList(),
            lastOperand = "0",
            lastOperator  = null,
            activeButton = null,
        )
    }

    @Nested
    inner class ModifyWith {

        @Test
        fun `should apply first matching transformation`() {
            // Arrange:
            val initialState = state.copy(lastOperand = "5")

            // Act:
            val newState = initialState.modifyWith(
                { initialState.lastOperand == "5" } to { copy(lastOperator = ButtonCalculatorBinary.Division) },
                { initialState.lastOperand == "10" } to { copy(lastOperand = "15") }
            )
            // Assert:
            ButtonCalculatorBinary.Division shouldBe newState.lastOperator
        }

        @Test
        fun `should not modify state if no conditions match`() {
            // Arrange & Act:
            val newState = state.modifyWith(
                { state.lastOperand == "329" } to { copy(lastOperand = "200") }
            )

            // Assert:
            state.lastOperand shouldBeEqual newState.lastOperand
        }

        @Test
        fun `should apply only the first matching transformation`() {
            // Arrange & Act:
            val newState = state.modifyWith(
                { true } to { copy(lastOperand = "10") },
                { true } to { copy(lastOperand = "20") }
            )
            // Assert:
            "10" shouldBeEqual newState.lastOperand
        }

        @Test
        fun `modifyWith should set hasError to true when exception occurs`() {
            // Arrange:
            val initialState = state.copy(hasError = false, errorMessage = null)

            // Act:
            val resultState = initialState.modifyWith(
                { true } to { throw IllegalArgumentException("Test error") },
            )
            // Assert:
            resultState.hasError shouldBe true
            resultState.errorMessage shouldBe "Test error"
        }

        @Test
        fun `modifyWith should use custom error message when provided`() {
            val initialState = state.copy(hasError = false, errorMessage = null)

            // Act:
            val resultState = initialState.modifyWith(
                { true } to { throw IllegalArgumentException("Test error") },
                errorMessage = "Custom error"
            )
            // Assert:
            resultState.hasError shouldBe true
            resultState.errorMessage shouldBe "Custom error"
        }
    }

    @Nested
    inner class CalculatorStatePropertiesTest {
        // Arrange: Setup test data (button instance)
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsButton.provideButtonButton().asStream()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should update active button correctly`(
            button: Button,
            expectedButton: Button,
        ) {
            // Act:
            val initialState = state.copy(activeButton = button)

            // Assert:
            expectedButton shouldBe initialState.activeButton
        }

        @Test
        fun `should return the correct property for calculator state`() {

            // Arrange & Act & Assert:
            withClue("Expected expression to be empty by default.") {
                state.expression shouldBe emptyList()
            }
            withClue("Expected lastOperand to be '0' by default.") {
                state.lastOperand shouldBe "0"
            }
            withClue("Expected operation to be null by default.") {
                state.lastOperator shouldBe null
            }
            withClue("Expected activeButton to be empty by default.") {
                state.activeButton shouldBe null
            }
        }

        @Test
        fun `should update lastOperand correctly`() {
            // Arrange & Act:
            val initialState = state.copy(lastOperand = "5")

            // Assert:
            "5" shouldBe initialState.lastOperand
        }

        @Test
        fun `should update expression correctly`() {
            // Arrange & Act
            val initialState = state.copy(expression = listOf("10"))

            // Assert
            "10" shouldBe initialState.expression[0]
        }
    }
}