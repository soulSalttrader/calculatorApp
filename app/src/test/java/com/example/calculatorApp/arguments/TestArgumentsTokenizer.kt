package com.example.calculatorApp.arguments

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.isBinary
import com.example.calculatorApp.domain.ast.TokenizerUtils.isNumber
import com.example.calculatorApp.domain.ast.TokenizerUtils.isParenthesis
import com.example.calculatorApp.domain.ast.TokenizerUtils.isUnaryPrefix
import com.example.calculatorApp.domain.ast.TokenizerUtils.isUnarySuffix
import com.example.calculatorApp.domain.ast.TokenizerUtils.toBinaryOperator
import com.example.calculatorApp.domain.ast.TokenizerUtils.toParenthesisOperator
import com.example.calculatorApp.domain.ast.TokenizerUtils.toUnaryOperator
import com.example.calculatorApp.model.symbols.HasSymbol
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputToken
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataTokenizerSeq.tokensBinaryTest
import com.example.calculatorApp.testData.TestDataTokenizerSeq.tokensNumberTest
import com.example.calculatorApp.testData.TestDataTokenizerSeq.tokensParenthesisTest
import com.example.calculatorApp.testData.TestDataTokenizerSeq.tokensUnaryPrefixTest
import com.example.calculatorApp.testData.TestDataTokenizerSeq.tokensUnarySuffixTest
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedToken
import kotlin.collections.addAll

object TestArgumentsTokenizer {

    fun provideTokenizerTestCases(
        symbolSources: Sequence<HasSymbol>,
    ): Sequence<TestCase<Input, Expected>> =
        sequence {
            symbolSources.forEach { source ->
                val token = source.symbol.label.toSingleToken()
                yield(
                    TestCase(
                        InputToken(listOf(source)),
                        ExpectedToken(listOf(token))
                    )
                )
            }
        }

    fun expectedMixed(): List<Token> = buildList {
        addAll(tokensNumberTest)
        addAll(tokensBinaryTest)
        addAll(tokensUnaryPrefixTest)
        addAll(tokensUnarySuffixTest)
        addAll(tokensParenthesisTest)
    }

    private fun String.toSingleToken(): Token = when {
        isNumber() -> Token.Number(toDouble())
        isBinary() -> Token.Binary(toBinaryOperator())
        isUnaryPrefix() -> Token.Unary(toUnaryOperator())
        isUnarySuffix() -> Token.Unary(toUnaryOperator())
        isParenthesis() -> Token.Parenthesis(toParenthesisOperator())
        else -> error("Unknown token type: $this")
    }
}