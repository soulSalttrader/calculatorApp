package com.example.calculatorApp.arguments

import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.model.symbols.HasSymbol
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataSymbolButton.symbolOperatorsMapGenerated
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedTokenUtils
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputTokenUtils
import kotlin.reflect.KClass

object TestArgumentsTokenizerUtils {

    fun provideTokenUtilsTestCasesLabels(
        hasSymbolClass: KClass<out HasSymbol>,
        matchingSource: List<SymbolButton>,
        shouldMatch: Boolean = true
    ): Sequence<TestCase<Input, Expected>> {
        val matchingLabels = matchingSource.map { it.label }.toSet()
        return hasSymbolClass.sealedSubclasses
            .asSequence()
            .mapNotNull { it.objectInstance }
            .map { source ->
                val labelInSet = source.symbol.label in matchingLabels
                val matches = if (shouldMatch) labelInSet else !labelInSet
                TestCase(
                    InputTokenUtils(source = source),
                    ExpectedTokenUtils(matches = matches)
                )
            }
    }

    fun provideTokenUtilsTestCasesOperators(
        hasSymbolClass: KClass<out HasSymbol>,
        symbolOperatorMap: Map<String, Operator?> = symbolOperatorsMapGenerated,
    ): Sequence<TestCase<Input, Expected>> =
        hasSymbolClass.sealedSubclasses
            .asSequence()
            .mapNotNull { it.objectInstance }
            .map { source ->
                val operator = symbolOperatorMap[source.symbol.label] ?: error("No operator found for label: ${source.symbol.label}")
                TestCase(
                    InputTokenUtils(source = source),
                    ExpectedTokenUtils(operator = operator)
                )
            }
}