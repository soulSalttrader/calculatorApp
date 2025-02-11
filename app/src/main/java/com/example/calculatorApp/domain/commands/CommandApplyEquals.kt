package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineMath
import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.state.CalculatorState
import com.example.calculatorApp.utils.Constants.MAX_NUM_LENGTH

class CommandApplyEquals(private val engine: EngineMath) : Command {

    override fun execute(state: CalculatorState): CalculatorState {
        val left = state.previousNumber.toDoubleOrNull()
        val right = state.currentNumber.toDoubleOrNull()

        if (left != null && right != null && state.operation != null) {
            val result = engine.applyArithmetic(left, right, state.operation as ButtonCalculatorArithmetic)

            return state.copy(
                currentNumber = result.toString().take(MAX_NUM_LENGTH),
                previousNumber = "",
                operation = null,
            )
        }

        return state
    }
}