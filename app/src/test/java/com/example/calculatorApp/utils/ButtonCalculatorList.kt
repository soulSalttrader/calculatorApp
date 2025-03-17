package com.example.calculatorApp.utils

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorParenthesis
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import io.mockk.InternalPlatformDsl.toArray
import kotlin.reflect.KClass

object ButtonCalculatorList {
    fun provideSequenceButtons(buttonClass: KClass<out Button>): Sequence<Button> {
        return try {
            buttonClass.sealedSubclasses
                .asSequence()
                .mapNotNull { it.objectInstance }
        } catch (e: Exception) {
            throw IllegalArgumentException("Unknown button class: $buttonClass.")
        }
    }

    val binary = provideSequenceButtons(ButtonCalculatorBinary::class)
    val unary = provideSequenceButtons(ButtonCalculatorUnary::class)
    val controls = provideSequenceButtons(ButtonCalculatorControl::class)
    val numbers = provideSequenceButtons(ButtonCalculatorNumber::class)
    val parenthesis = provideSequenceButtons(ButtonCalculatorParenthesis::class)

    val allButtons: Sequence<Button> = sequenceOf(
        binary,
        unary,
        controls,
        numbers,
        parenthesis,
    ).flatMap { it }
}