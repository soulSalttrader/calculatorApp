package com.example.calculatorApp.model.state

import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.symbols.SymbolButton

data class CalculatorState(
    val expression: List<Token> = emptyList(),
    val lastOperand: String = SymbolButton.ZERO.label,
    val lastResult: String? = null,
    val lastOperator: Operator? = null,
    val cachedOperand: String? = null,
    val activeButton: Button? = null,
    val isComputed: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: String? = null,
) {
    fun modifyWith(
        vararg transformations: Pair<() -> Boolean, CalculatorState.() -> CalculatorState>,
        errorMessage: String? = null,
    ): CalculatorState {
        return try {
            transformations
                .firstOrNull { it.first() }
                ?.second?.invoke(this)
                ?: this
        } catch (e: Exception) {
            this.copy(hasError = true, errorMessage = errorMessage ?: e.message)
        }
    }
}