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
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataTokenizerSeq.tokensBinaryTest
import com.example.calculatorApp.testData.TestDataTokenizerSeq.tokensNumberTest
import com.example.calculatorApp.testData.TestDataTokenizerSeq.tokensParenthesisTest
import com.example.calculatorApp.testData.TestDataTokenizerSeq.tokensUnaryPrefixTest
import com.example.calculatorApp.testData.TestDataTokenizerSeq.tokensUnarySuffixTest
import kotlin.collections.addAll

object TestArgumentsTokenizer {

    fun provideTokenizerTestCases(
        expressions: Sequence<List<String>>,
    ): Sequence<TestCase<List<String>, List<Token>>> =
        sequence {
            expressions.forEach { label ->
                val token = label.joinToString().toSingleToken()
                yield(TestCase(label, listOf(token)))
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