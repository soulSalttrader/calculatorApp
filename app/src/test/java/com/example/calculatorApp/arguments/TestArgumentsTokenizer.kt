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
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedToken
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputToken

object TestArgumentsTokenizer {

    fun provideTokenizerTestCases(
        vararg symbolSources: Sequence<HasSymbol>,
    ): Sequence<TestCase<Input, Expected>> =
        symbolSources
            .asSequence()
            .flatten()
            .map { source ->
                val token = source.symbol.label.toSingleToken()
                TestCase(
                    InputToken(listOf(source)),
                    ExpectedToken(listOf(token))
                )
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