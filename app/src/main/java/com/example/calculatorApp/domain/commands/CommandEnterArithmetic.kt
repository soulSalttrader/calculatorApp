package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.state.CalculatorState

class CommandEnterArithmetic(
    private val operation: ButtonCalculatorArithmetic,
    private val engineState: EngineState,
) : Command {

    override fun execute(state: CalculatorState): CalculatorState {
        return engineState.enterArithmetic(state, operation)
    }
}