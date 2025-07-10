package com.example.calculatorApp.arguments

import com.example.calculatorApp.testData.TestDataTokenizerStandard
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.symbols.SymbolButton
import kotlin.reflect.KClass

object TestArgumentsTokenizerStandard {

    fun provideSymbolLabels(): Sequence<String> {
        return SymbolButton.entries
            .asSequence()
            .map { it.label }
            .filter { it !in sequenceOf("AC", "C", ",", "=") }
    }

    private fun provideButtonLabels(buttonClass: KClass<out Button>?): Sequence<String> {
        return buttonClass?.sealedSubclasses
            ?.asSequence()
            ?.mapNotNull { it.objectInstance }
            ?.sortedBy { (it as? ButtonCalculatorNumber)?.symbol?.label?.toDoubleOrNull() }
            ?.map { it.symbol.label } ?: throw IllegalArgumentException("KClass<out Button> not provided.")
    }

    fun provide(
        buttonClass: KClass<out Button>? = null,
        expression: Sequence<String> = provideButtonLabels(buttonClass),
    ): Sequence<TestDataTokenizerStandard> {
        return expression.map { TestDataTokenizerStandard(expression = listOf(it)) }
    }
}