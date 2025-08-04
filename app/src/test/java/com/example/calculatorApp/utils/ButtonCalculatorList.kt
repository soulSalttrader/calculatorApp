package com.example.calculatorApp.utils

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorParenthesis
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.utils.TestUtils.provideSequenceOfSingletons

object ButtonCalculatorList {

    val binary = provideSequenceOfSingletons(ButtonCalculatorBinary::class)
    val unary = provideSequenceOfSingletons(ButtonCalculatorUnary::class)
    val controls = provideSequenceOfSingletons(ButtonCalculatorControl::class)
    val numbers = provideSequenceOfSingletons(ButtonCalculatorNumber::class)
    val parenthesis = provideSequenceOfSingletons(ButtonCalculatorParenthesis::class)

    val allButtons: Sequence<Button> = sequenceOf(
        binary,
        unary,
        controls,
        numbers,
        parenthesis,
    ).flatMap { it }
}