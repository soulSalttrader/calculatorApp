package com.example.calculatorApp.utils

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber

object ButtonCalculatorList {
    val arithmetics: Array<ButtonCalculatorArithmetic> = ButtonCalculatorArithmetic::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
        .toTypedArray()

    val controls: Array<ButtonCalculatorControl> = ButtonCalculatorControl::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
        .toTypedArray()

    val numbers: Array<ButtonCalculatorNumber> = ButtonCalculatorNumber::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
        .toTypedArray()
}