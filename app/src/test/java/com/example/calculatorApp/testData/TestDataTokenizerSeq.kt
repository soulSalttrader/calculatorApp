package com.example.calculatorApp.testData

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toBinaryOperator
import com.example.calculatorApp.domain.ast.TokenizerUtils.toParenthesisOperator
import com.example.calculatorApp.domain.ast.TokenizerUtils.toUnaryOperator
import com.example.calculatorApp.model.symbols.HasSymbol
import com.example.calculatorApp.testData.TestDataElementSeq.buttonsNumbersTest
import com.example.calculatorApp.testData.TestDataElementSeq.operatorsBinaryTest
import com.example.calculatorApp.testData.TestDataElementSeq.operatorsParenthesisTest
import com.example.calculatorApp.testData.TestDataElementSeq.operatorsUnaryPrefixTest
import com.example.calculatorApp.testData.TestDataElementSeq.operatorsUnarySuffixTest

object TestDataTokenizerSeq {

    private inline fun <reified T : HasSymbol, R : Token> Sequence<T>.toTokens(
        noinline transform: (String) -> R
    ): Sequence<R> = map { transform(it.symbol.label) }

    val tokensNumberTest = buttonsNumbersTest.toTokens { Token.Number(it.toDouble()) }
    val tokensBinaryTest = operatorsBinaryTest.toTokens { Token.Binary(it.toBinaryOperator()) }
    val tokensUnaryPrefixTest = operatorsUnaryPrefixTest.toTokens { Token.Unary(it.toUnaryOperator()) }
    val tokensUnarySuffixTest = operatorsUnarySuffixTest.toTokens { Token.Unary(it.toUnaryOperator()) }
    val tokensParenthesisTest = operatorsParenthesisTest.toTokens { Token.Parenthesis(it.toParenthesisOperator()) }

    val expectedMixed: List<Token> = buildList {
        addAll(tokensNumberTest)
        addAll(tokensBinaryTest)
        addAll(tokensUnaryPrefixTest)
        addAll(tokensUnarySuffixTest)
        addAll(tokensParenthesisTest)
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