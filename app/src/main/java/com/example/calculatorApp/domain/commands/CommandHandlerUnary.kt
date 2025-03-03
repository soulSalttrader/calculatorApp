package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.state.CalculatorState

class CommandHandlerUnary(
    private val engineState: EngineState,
    private val operation: ButtonCalculatorUnary
) : Command {

    override fun execute(state: CalculatorState): CalculatorState {
        return engineState.handleUnary(state, operation)
    }
}