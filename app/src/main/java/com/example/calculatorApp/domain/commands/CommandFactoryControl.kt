package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineMath
import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl

class CommandFactoryControl(
    private val engineMath: EngineMath,
    private val engineState: EngineState,
) : CommandFactorySub<ButtonCalculatorControl> {

    override fun create(button: ButtonCalculatorControl): Command {

        return when (button) {
            is ButtonCalculatorControl.Decimal -> CommandEnterDecimal(engineState)
            is ButtonCalculatorControl.AllClear -> CommandApplyClearAll(engineState)
            is ButtonCalculatorControl.Clear -> CommandApplyClear(engineState)
            is ButtonCalculatorControl.Percent -> CommandApplyPercent(engineMath)
            is ButtonCalculatorControl.PlusMinus -> CommandApplySign(engineMath)
        }
    }
}