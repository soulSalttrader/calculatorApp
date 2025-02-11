package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineMath
import com.example.calculatorApp.model.state.CalculatorState

class CommandApplyPercent(private val engine: EngineMath) : Command {

    override fun execute(state: CalculatorState): CalculatorState {
        val number = state.currentNumber.toDoubleOrNull() ?: return state
        val percent = engine.applyPercent(number)
        return state.copy(currentNumber = percent.toString())
    }
}