package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.state.CalculatorState

class CommandEnterArithmetic(
    private val engineState: EngineState,
    private val arithmetic: ButtonCalculatorBinary,
) : Command {

    override fun execute(state: CalculatorState): CalculatorState {
        return engineState.enterArithmetic(state, arithmetic)
    }
}