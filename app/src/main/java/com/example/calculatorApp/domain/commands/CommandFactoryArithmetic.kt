package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic

class CommandFactoryArithmetic(
    private val engineState: EngineState
) : CommandFactorySub<ButtonCalculatorArithmetic> {

    override fun create(button: ButtonCalculatorArithmetic): Command {

        return when (button) {
            is ButtonCalculatorArithmetic.Equals -> CommandApplyEquals(engineState)
            else -> CommandHandlerArithmetic(engineState, button)
        }
    }
}