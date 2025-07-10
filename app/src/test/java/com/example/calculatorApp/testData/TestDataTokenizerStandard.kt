package com.example.calculatorApp.testData

import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.OperatorParenthesis
import com.example.calculatorApp.domain.ast.OperatorUnary
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toBinaryOperator
import com.example.calculatorApp.domain.ast.TokenizerUtils.toParenthesisOperator
import com.example.calculatorApp.domain.ast.TokenizerUtils.toUnaryOperator

data class TestDataTokenizerStandard(val expression: List<String>) {

    fun expectedNumber(): List<Token.Number> = listOf(
        Token.Number(value = expression.joinToString().toDouble())
    )

    fun expectedBinary(): List<Token.Binary> = listOf(
        Token.Binary(operator = expression.joinToString().toBinaryOperator())
    )

    fun expectedUnary(): List<Token.Unary> = listOf(
        Token.Unary(operator = expression.joinToString().toUnaryOperator())
    )

    fun expectedParenthesis(): List<Token.Parenthesis> = listOf(
        Token.Parenthesis(type = expression.joinToString().toParenthesisOperator())
    )

    companion object {
        fun expectedMixed(): List<Token> {
            return listOf(
                Token.Binary(operator = OperatorBinary.Division),
                Token.Binary(operator = OperatorBinary.Multiplication),
                Token.Binary(operator = OperatorBinary.Subtraction),
                Token.Binary(operator = OperatorBinary.Addition),
                Token.Unary(operator = OperatorUnary.Prefix.Sign),
                Token.Unary(operator = OperatorUnary.Suffix.Percentage),
                Token.Number(value = 0.0),
                Token.Number(value = 1.0),
                Token.Number(value = 2.0),
                Token.Number(value = 3.0),
                Token.Number(value = 4.0),
                Token.Number(value = 5.0),
                Token.Number(value = 6.0),
                Token.Number(value = 7.0),
                Token.Number(value = 8.0),
                Token.Number(value = 9.0),
                Token.Parenthesis(type = OperatorParenthesis.Open),
                Token.Parenthesis(type = OperatorParenthesis.Close),
            )
        }
    }
}