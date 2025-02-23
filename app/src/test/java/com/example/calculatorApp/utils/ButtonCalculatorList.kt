package com.example.calculatorApp.utils

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorParenthesis
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary

object ButtonCalculatorList {
    val binary: Array<ButtonCalculatorBinary> = ButtonCalculatorBinary::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
        .toTypedArray()

    val unary: Array<ButtonCalculatorUnary> = ButtonCalculatorUnary::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
        .toTypedArray()

    val controls: Array<ButtonCalculatorControl> = ButtonCalculatorControl::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
        .toTypedArray()

    val numbers: Array<ButtonCalculatorNumber> = ButtonCalculatorNumber::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
        .toTypedArray()

    val parenthesis: Array<ButtonCalculatorParenthesis> = ButtonCalculatorParenthesis::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
        .toTypedArray()

    val allButtons: Array<Button> = arrayOf(
        *binary,
        *unary,
        *controls,
        *numbers,
        *parenthesis,
    )
}