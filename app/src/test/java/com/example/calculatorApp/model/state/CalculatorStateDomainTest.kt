package com.example.calculatorApp.model.state

import com.example.calculatorApp.domain.ast.TokenizerUtils.toBinaryOperator
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import io.kotest.assertions.withClue
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CalculatorStateDomainTest {

    private lateinit var state: CalculatorStateDomain

    @BeforeEach
    fun setUp() {
        // Arrange: Default Calculator state
        state = CalculatorStateDomain()
    }

    @Nested
    inner class ModifyWith {

        @Test
        fun `should apply first matching transformation`() {
            // Arrange:
            val initialState = state.copy(lastOperand = "1")
            // Act:
            val newState = initialState.modifyWith(
                { initialState.lastOperand == "50" } to { copy(lastOperand = "29") },
                { initialState.lastOperand == "1" } to { copy(lastOperator = ButtonCalculatorBinary.Division.toBinaryOperator()) },
            )
            // Assert:
            newState.lastOperator shouldBe ButtonCalculatorBinary.Division.symbol.label.toBinaryOperator()
            newState.lastOperand shouldBe "1"
            newState.lastOperand shouldNotBe "29"
        }

        @Test
        fun `should not modify state if no conditions match`() {
            // Arrange:
            val initialState = state.copy(lastOperand = "1")
            // & Act:
            val newState = initialState.modifyWith(
                { false } to { copy(lastOperand = "200") },
                { false } to { copy(lastOperand = "300") }
            )
            // Assert:
            newState shouldBe initialState
        }

        @Test
        fun `should apply only the first matching transformation`() {
            // Arrange & Act:
            val newState = state.modifyWith(
                { true } to { copy(lastOperand = "10") },
                { true } to { copy(lastOperand = "20") }
            )
            // Assert:
            newState.lastOperand shouldBeEqual "10"
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
    inner class CalculatorStateDomainPropertiesTest {
        @Test
        fun `should have default empty domain calculator state`() {
            // Arrange & Act & Assert:
            withClue("Expected expression to be empty by default.") {
                state.expression shouldBe emptyList()
            }
            withClue("Expected lastOperand to be '0' by default.") {
                state.lastOperand shouldBe "0"
            }
            withClue("Expected lastResult null by default.") {
                state.lastResult shouldBe null
            }
            withClue("Expected operation to be null by default.") {
                state.lastOperator shouldBe null
            }
            withClue("Expected cachedOperand null by default.") {
                state.cachedOperand shouldBe null
            }
            withClue("Expected activeButton to be empty by default.") {
                state.activeButton shouldBe null
            }
            withClue("Expected isComputed false by default.") {
                state.isComputed shouldBe false
            }
            withClue("Expected hasError false by default.") {
                state.hasError shouldBe false
            }
            withClue("Expected errorMessage null by default.") {
                state.errorMessage shouldBe null
            }
        }
    }
}