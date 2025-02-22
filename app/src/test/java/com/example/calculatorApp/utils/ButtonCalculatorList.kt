package com.example.calculatorApp.utils

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber

object ButtonCalculatorList {
    val arithmetics: Array<ButtonCalculatorBinary> = ButtonCalculatorBinary::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
        .toTypedArray()

    val controls: Array<ButtonCalculatorControl> = ButtonCalculatorControl::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
        .toTypedArray()

    val numbers: Array<ButtonCalculatorNumber> = ButtonCalculatorNumber::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
        .toTypedArray()

    val allButtons: Array<Button> = arrayOf(
        *arithmetics,
        *controls,
        *numbers,
    )
}