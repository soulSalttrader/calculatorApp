package com.example.calculatorApp.testData

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toBinaryOperator
import com.example.calculatorApp.domain.ast.TokenizerUtils.toParenthesisOperator
import com.example.calculatorApp.domain.ast.TokenizerUtils.toUnaryOperator
import com.example.calculatorApp.model.symbols.HasSymbol
import com.example.calculatorApp.model.symbols.SymbolButton
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

    fun seqSymbolsAllTest(
        filter: Sequence<String> = sequenceOf(
            SymbolButton.ALL_CLEAR.label,
            SymbolButton.CLEAR.label,
            SymbolButton.DECIMAL.label,
            SymbolButton.EQUALS.label,
        )
    ): List<String> = SymbolButton.entries
        .asSequence()
        .map { it.label }
        .filter { it !in filter }
        .toList()
}