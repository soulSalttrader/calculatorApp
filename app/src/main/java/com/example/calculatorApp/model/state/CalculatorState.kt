package com.example.calculatorApp.model.state

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.symbols.SymbolButton

data class CalculatorState(
    val expression: List<String> = emptyList(),
    val lastInput: String = SymbolButton.ZERO.label,
    val lastResult: String? = null,
    val subResult: String? = null,
    val lastOperator: Button? = null,
    val pendingOperator: Button? = null,
    val activeButton: Button? = null,
    val isComputed: Boolean = false,
) {
    fun modifyWith(vararg transformations: Pair<() -> Boolean, CalculatorState.() -> CalculatorState>): CalculatorState {
        for ((condition, action) in transformations) {
            if (condition()) return action()
        }
        return this
    }
}
