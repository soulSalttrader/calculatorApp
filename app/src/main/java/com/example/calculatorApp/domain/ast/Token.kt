package com.example.calculatorApp.domain.ast

sealed class Token {
    data class Number(val value: Double) : Token()
    data class Binary(val operator: OperatorBinary) : Token()
    data class Unary(val operator: OperatorUnary) : Token()
    data class Parenthesis(val type: OperatorParenthesis) : Token()
}