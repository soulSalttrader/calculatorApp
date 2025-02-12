package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.state.CalculatorState

class CommandEnterNumber(
    private val engine: EngineState,
    private val number: Int,
) : Command {

    override fun execute(state: CalculatorState): CalculatorState {
        return engine.enterNumber(state, number)
    }
}