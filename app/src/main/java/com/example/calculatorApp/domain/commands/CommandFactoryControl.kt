package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl

class CommandFactoryControl(
    private val engineState: EngineState,
) : CommandFactorySub<ButtonCalculatorControl> {

    override fun create(button: ButtonCalculatorControl): Command {

        return when (button) {
            is ButtonCalculatorControl.Decimal -> CommandEnterDecimal(engineState)
            is ButtonCalculatorControl.AllClear -> CommandApplyClearAll(engineState)
            is ButtonCalculatorControl.Clear -> CommandApplyClear(engineState)
            is ButtonCalculatorControl.Percentage -> CommandApplyPercent(engineState)
            is ButtonCalculatorControl.Sign -> CommandApplySign(engineState)
        }
    }
}