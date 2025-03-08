package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.domain.actions.CalculatorAction

class CommandFactoryStandard(
    private val engineState: EngineState
) : CommandFactory {

    override fun createCommand(action: CalculatorAction): Command {
        return when (action) {
            is CalculatorAction.ButtonPressed -> CommandHandler(engineState, action.button)
        }
    }
}