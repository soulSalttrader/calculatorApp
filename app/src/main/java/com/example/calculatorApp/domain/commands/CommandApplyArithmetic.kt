package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineMath
import com.example.calculatorApp.model.state.CalculatorState

class CommandApplyArithmetic(private val engineMath: EngineMath) : Command {

    override fun execute(state: CalculatorState): CalculatorState {
        return CommandApplyEquals(engineMath).execute(state)
    }
}