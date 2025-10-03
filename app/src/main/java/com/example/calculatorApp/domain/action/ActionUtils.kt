package com.example.calculatorApp.domain.action

import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber

object ActionUtils {

    fun CalculatorAction.shouldResetTextSize() = when (this.button) {
        is ButtonCalculatorControl,
        is ButtonCalculatorNumber -> true

        else -> false
    }
}