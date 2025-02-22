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

class CalculatorStateTest {

    private lateinit var state: CalculatorState

    @BeforeEach
    fun setUp() {
        // Arrange:
        state = CalculatorState(
            lastInput = "0",
            lastOperator  = null,
            expression = emptyList(),
            activeButton = null,
        )
    }

    @Nested
    inner class ModifyWith {

        @Test
        fun `should apply first matching transformation`() {
            // Arrange:
            val modifiedState = state.copy(lastInput = "5")

            // Act:
            val newState = modifiedState.modifyWith(
                { modifiedState.lastInput == "5" } to { copy(lastOperator = ButtonCalculatorBinary.Division) },
                { modifiedState.lastInput == "10" } to { copy(lastInput = "15") }
            )
            // Assert:
            ButtonCalculatorBinary.Division shouldBe newState.lastOperator
        }

        @Test
        fun `should not modify state if no conditions match`() {
            // Arrange & Act:
            val newState = state.modifyWith(
                { state.lastInput == "329" } to { copy(lastInput = "200") }
            )

            // Assert:
            state.lastInput shouldBeEqual newState.lastInput
        }

        @Test
        fun `should apply only the first matching transformation`() {
            // Arrange & Act:
            val newState = state.modifyWith(
                { true } to { copy(lastInput = "10") },
                { true } to { copy(lastInput = "20") }
            )
            // Assert:
            "10" shouldBeEqual newState.lastInput
        }
    }

    @Nested
    inner class CalculatorStatePropertiesTest {
        // Arrange: Setup test data (button instance)
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsButton.provideButtonButton()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should update active button correctly`(
            button: Button,
            expectedButton: Button,
        ) {
            // Act:
            val modifiedState = state.copy(activeButton = button)

            // Assert:
            expectedButton shouldBe modifiedState.activeButton
        }

        @Test
        fun `should return the correct property for calculator state`() {

            // Arrange & Act & Assert:
            withClue("Expected lastInput to be '0' by default.") {
                state.lastInput shouldBe "0"
            }
            withClue("Expected expression to be empty by default.") {
                state.expression shouldBe emptyList()
            }
            withClue("Expected operation to be null by default.") {
                state.lastOperator shouldBe null
            }
            withClue("Expected activeButton to be empty by default.") {
                state.activeButton shouldBe null
            }
        }

        @Test
        fun `should update lastInput correctly`() {
            // Arrange & Act:
            val modifiedState = state.copy(lastInput = "5")

            // Assert:
            "5" shouldBe modifiedState.lastInput
        }

        @Test
        fun `should update expression correctly`() {
            // Arrange & Act
            val modifiedState = state.copy(expression = listOf("10"))

            // Assert
            "10" shouldBe modifiedState.expression[0]
        }
    }
}