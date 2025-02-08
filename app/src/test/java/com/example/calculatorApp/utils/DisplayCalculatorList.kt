package com.example.calculatorApp.utils

import com.example.calculatorApp.model.elements.display.DisplayCalculatorInput

object DisplayCalculatorList {
    val inputs: Array<DisplayCalculatorInput> = DisplayCalculatorInput::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
        .toTypedArray()
}