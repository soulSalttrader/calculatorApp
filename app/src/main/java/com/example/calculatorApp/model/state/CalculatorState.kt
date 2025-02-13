package com.example.calculatorApp.model.state

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.symbols.SymbolButton

data class CalculatorState(
    val operandRight: String = SymbolButton.ZERO.label,
    val operator: Button? = null,
    val operandLeft: String = "",
    val operand: String? = null,
    val activeLabel: String = "",
) {
    fun modifyWith(vararg transformations: Pair<() -> Boolean, CalculatorState.() -> CalculatorState>): CalculatorState {
        for ((condition, action) in transformations) {
            if (condition()) return action()
        }
        return this
    }
}
