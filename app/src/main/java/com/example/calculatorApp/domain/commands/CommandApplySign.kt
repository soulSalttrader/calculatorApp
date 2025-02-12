package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.state.CalculatorState

class CommandApplySign(private val engine: EngineState) : Command {

    override fun execute(state: CalculatorState): CalculatorState {
        return engine.applySign(state)
    }
}
