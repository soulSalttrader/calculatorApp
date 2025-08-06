package com.example.calculatorApp.arguments

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.testData.TestDataTokenizerStandard
import com.example.calculatorApp.utils.TestUtils.mapSingletonsTo
import kotlin.reflect.KClass

object TestArgumentsTokenizerStandard {

    fun provideSymbolLabels(): Sequence<String> {
        return SymbolButton.entries
            .asSequence()
            .map { it.label }
            .filter { it !in sequenceOf("AC", "C", ",", "=") }
    }

    fun <T : Button> provideStandard(
        type: KClass<out T>,
    ): Sequence<TestDataTokenizerStandard> = mapSingletonsTo(type) {
        TestDataTokenizerStandard(expression = listOf(it.symbol.label))
    }
}