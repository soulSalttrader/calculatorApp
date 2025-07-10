package com.example.calculatorApp.model.symbols

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber

object SymbolButtonUtils {

    private val arithmetics: Array<ButtonCalculatorBinary> = ButtonCalculatorBinary::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
        .toTypedArray()

    private val controls: Array<ButtonCalculatorControl> = ButtonCalculatorControl::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
        .toTypedArray()

    private val numbers: Array<ButtonCalculatorNumber> = ButtonCalculatorNumber::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
        .toTypedArray()

    private val allButtons: Array<Button> = arrayOf(
        *arithmetics,
        *controls,
        *numbers,
    )

    fun String.toButton(): Button? {
        val symbol = SymbolButton.entries.find { it.label == this }

        return symbol?.let {
            val map = allButtons.associateBy { it.symbol }
            map[symbol]
        }
    }

    fun generateRegexPattern(): String {
        val symbols = SymbolButton.entries
            .map { it.label }
            .joinToString("|") { Regex.escape(it) } // Join the labels with OR (|) operator

        return """($symbols)""" // Just use the symbols in parentheses
    }
}