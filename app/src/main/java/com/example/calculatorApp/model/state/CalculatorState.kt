package com.example.calculatorApp.model.state

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.symbols.SymbolButton

data class CalculatorState(
    val expression: List<String> = emptyList(),
    val lastOperand: String = SymbolButton.ZERO.label,
    val lastResult: String? = null,
    val lastOperator: Button? = null,
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
