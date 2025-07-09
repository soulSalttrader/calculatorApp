package com.example.calculatorApp.domain.ast

sealed class Token {
    data class Number(val value: Double) : Token()
    data class Binary(val operator: OperatorBinary) : Token()
    data class Unary(val operator: OperatorUnary) : Token()
    data class Parenthesis(val type: OperatorParenthesis) : Token()

    companion object {
        fun List<Token>.firstNumberOrNull(): Number? {
            return this.firstOrNull { it is Number } as? Number
        }

        fun List<Token>.lastNumberOrNull(): Number? {
            return this.findLast { it is Number } as? Number
        }
    }
}