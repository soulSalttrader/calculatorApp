package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary

class CommandFactoryUnary(
    private val engineState: EngineState
) : CommandFactorySub<ButtonCalculatorUnary> {

    override fun create(button: ButtonCalculatorUnary): Command {
        return CommandHandlerUnary(engineState, button)
    }
}