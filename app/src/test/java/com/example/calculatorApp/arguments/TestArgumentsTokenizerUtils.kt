package com.example.calculatorApp.arguments

import com.example.calculatorApp.model.symbols.HasSymbol
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedTokenUtils
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputTokenUtils
import kotlin.reflect.KClass

object TestArgumentsTokenizerUtils {

    fun provideTokenUtilsTestCases(
        symbolClass: KClass<out HasSymbol>,
        matchingLabels: Set<String>,
        shouldMatch: Boolean = true,
    ): Sequence<TestCase<Input, Expected>> =
        symbolClass.sealedSubclasses
            .asSequence()
            .mapNotNull { it.objectInstance }
            .map { source ->
                val labelInSet = source.symbol.label in matchingLabels
                val matchesSymbol = if (shouldMatch) labelInSet else !labelInSet
                TestCase(
                    InputTokenUtils(source.symbol.label),
                    ExpectedTokenUtils(matchesSymbol)
                )
            }
}