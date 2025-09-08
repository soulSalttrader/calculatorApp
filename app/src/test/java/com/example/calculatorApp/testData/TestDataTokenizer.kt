package com.example.calculatorApp.testData

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toBinaryOperator
import com.example.calculatorApp.domain.ast.TokenizerUtils.toParenthesisOperator
import com.example.calculatorApp.domain.ast.TokenizerUtils.toUnaryOperator
import com.example.calculatorApp.model.symbols.HasSymbol
import com.example.calculatorApp.testData.TestDataElement.buttonsNumbersTest
import com.example.calculatorApp.testData.TestDataElement.operatorsBinaryTest
import com.example.calculatorApp.testData.TestDataElement.operatorsParenthesisTest
import com.example.calculatorApp.testData.TestDataElement.operatorsUnaryPrefixTest
import com.example.calculatorApp.testData.TestDataElement.operatorsUnarySuffixTest

object TestDataTokenizer {

    private inline fun <reified T : HasSymbol, R : Token> Sequence<T>.toTokens(
        noinline transform: (String) -> R
    ): Sequence<R> = map { transform(it.symbol.label) }

    val expectedMixed: List<Token> = buildList {
        addAll(buttonsNumbersTest.toTokens { Token.Number(it.toDouble()) })
        addAll(operatorsBinaryTest.toTokens { Token.Binary(it.toBinaryOperator()) })
        addAll(operatorsUnaryPrefixTest.toTokens { Token.Unary(it.toUnaryOperator()) })
        addAll(operatorsUnarySuffixTest.toTokens { Token.Unary(it.toUnaryOperator()) })
        addAll(operatorsParenthesisTest.toTokens { Token.Parenthesis(it.toParenthesisOperator()) })
    }

    val inputMixed: List<String> = sequenceOf(
        buttonsNumbersTest,
        operatorsBinaryTest,
        operatorsUnaryPrefixTest,
        operatorsUnarySuffixTest,
        operatorsParenthesisTest,
    )
        .flatten()
        .map { it.symbol.label }
        .toList()
}