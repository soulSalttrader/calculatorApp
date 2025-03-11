package com.example.calculatorApp.domain.ast

import com.example.calculatorApp.domain.ast.TokenizerUtils.isBinary
import com.example.calculatorApp.domain.ast.TokenizerUtils.isNumber
import com.example.calculatorApp.domain.ast.TokenizerUtils.isParenthesis
import com.example.calculatorApp.domain.ast.TokenizerUtils.isUnaryPrefix
import com.example.calculatorApp.domain.ast.TokenizerUtils.isUnarySuffix
import com.example.calculatorApp.domain.ast.TokenizerUtils.toBinaryOperator
import com.example.calculatorApp.domain.ast.TokenizerUtils.toParenthesisOperator
import com.example.calculatorApp.domain.ast.TokenizerUtils.toUnaryOperator

class TokenizerStandard : Tokenizer {

    override fun tokenize(expression: List<String>): List<Token> {
        val tokens = mutableListOf<Token>()

        for (token in expression) {
            tokens.add(
                when {
                    token.isNumber() -> Token.Number(token.toDouble())
                    token.isBinary() -> Token.Binary(token.toBinaryOperator())
                    token.isParenthesis() -> Token.Parenthesis(token.toParenthesisOperator())
                    token.isUnaryPrefix() || token.isUnarySuffix() -> Token.Unary(token.toUnaryOperator())

                    else -> throw IllegalArgumentException("Invalid token: $token")
                }
            )
        }

        return tokens
    }
}