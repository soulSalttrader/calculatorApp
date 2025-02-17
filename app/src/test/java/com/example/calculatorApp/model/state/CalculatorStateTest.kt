package com.example.calculatorApp.model.state

import com.example.calculatorApp.arguments.TestArgumentsButton
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
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
            operandRight = "0",
            operator  = null,
            operandLeft = "",
            activeButton = null,
        )
    }

    @Nested
    inner class ModifyWith {

        @Test
        fun `should apply first matching transformation`() {
            // Arrange:
            val modifiedState = state.copy(operandRight = "5")

            // Act:
            val newState = modifiedState.modifyWith(
                { modifiedState.operandRight == "5" } to { copy(operator = ButtonCalculatorArithmetic.Division) },
                { modifiedState.operandRight == "10" } to { copy(operandRight = "15") }
            )
            // Assert:
            ButtonCalculatorArithmetic.Division shouldBe newState.operator
        }

        @Test
        fun `should not modify state if no conditions match`() {
            // Arrange & Act:
            val newState = state.modifyWith(
                { state.operandRight == "329" } to { copy(operandRight = "200") }
            )

            // Assert:
            state.operandRight shouldBeEqual newState.operandRight
        }

        @Test
        fun `should apply only the first matching transformation`() {
            // Arrange & Act:
            val newState = state.modifyWith(
                { true } to { copy(operandRight = "10") },
                { true } to { copy(operandRight = "20") }
            )
            // Assert:
            "10" shouldBeEqual newState.operandRight
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
            withClue("Expected operandRight to be '0' by default.") {
                state.operandRight shouldBe "0"
            }
            withClue("Expected operandLeft to be empty by default.") {
                state.operandLeft shouldBe ""
            }
            withClue("Expected operation to be null by default.") {
                state.operator shouldBe null
            }
            withClue("Expected activeButton to be empty by default.") {
                state.activeButton shouldBe null
            }
        }

        @Test
        fun `should update operandRight correctly`() {
            // Arrange & Act:
            val modifiedState = state.copy(operandRight = "5")

            // Assert:
            "5" shouldBe modifiedState.operandRight
        }

        @Test
        fun `should update operandLeft correctly`() {
            // Arrange & Act
            val modifiedState = state.copy(operandLeft = "10")

            // Assert
            "10" shouldBe modifiedState.operandLeft
        }
    }
}