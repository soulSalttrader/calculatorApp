package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber

class CommandFactoryNumber(
    private val engineState: EngineState
) : CommandFactorySub<ButtonCalculatorNumber> {

    override fun create(button: ButtonCalculatorNumber): Command {
        return CommandHandlerNumber(engineState, button)
    }
}