package com.example.calculatorApp.model.state

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.symbols.SymbolButton

data class CalculatorState(
    val currentNumber: String = SymbolButton.ZERO.label,
    val operation: Button? = null,
    val previousNumber: String = "",
    val activeButtonLabel: String = "",
) {
    fun modifyWith(vararg transformations: Pair<() -> Boolean, CalculatorState.() -> CalculatorState>): CalculatorState {
        for ((condition, action) in transformations) {
            if (condition()) return action()
        }
        return this
    }
}
