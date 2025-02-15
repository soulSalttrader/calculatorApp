package com.example.calculatorApp.model.state

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.symbols.SymbolButton

data class CalculatorState(
    val operandLeft: String = "",
    val operator: Button? = null,
    val operandRight: String = SymbolButton.ZERO.label,
    val operand: String? = null,
    val activeButton: Button? = null,
) {
    fun modifyWith(vararg transformations: Pair<() -> Boolean, CalculatorState.() -> CalculatorState>): CalculatorState {
        for ((condition, action) in transformations) {
            if (condition()) return action()
        }
        return this
    }
}
