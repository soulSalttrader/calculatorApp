package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary

class CommandFactoryArithmetic(
    private val engineState: EngineState
) : CommandFactorySub<ButtonCalculatorBinary> {

    override fun create(button: ButtonCalculatorBinary): Command {
        return CommandHandlerArithmetic(engineState, button)
    }
}