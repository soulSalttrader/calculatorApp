package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineMath
import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic

class CommandFactoryArithmetic(private val engineState: EngineState, private val engineMath: EngineMath) : CommandFactorySub<ButtonCalculatorArithmetic> {

    override fun create(button: ButtonCalculatorArithmetic): Command {

        return when (button) {
            is ButtonCalculatorArithmetic.Equals -> CommandApplyEquals(engineMath)
            else -> CommandChain(
                CommandApplyArithmetic(engineMath),
                CommandEnterArithmetic(button, engineState)
            )
        }
    }
}