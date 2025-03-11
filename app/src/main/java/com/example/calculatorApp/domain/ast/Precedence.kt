package com.example.calculatorApp.domain.ast

sealed class Precedence(val level: Int) {

    data object Lowest : Precedence(0)  // Default for unknown tokens
    data object Sum : Precedence(1)     // +, -
    data object Product : Precedence(2) // *, /
    data object Prefix : Precedence(3)  // -x, +x
    data object Power : Precedence(5)   // x^y
    data object Suffix : Precedence(4)  // x!, x², %
    data object Group : Precedence(6)   // (, )

    companion object {
        fun fromToken(token: Token): Precedence {
            return when (token) {
                is Token.Binary -> when (token.operator) {
                    OperatorBinary.Addition,
                    OperatorBinary.Subtraction -> Sum

                    OperatorBinary.Multiplication,
                    OperatorBinary.Division -> Product
                }

                is Token.Unary -> when (token.operator) {
                    OperatorUnary.Prefix.Sign -> Prefix
                    OperatorUnary.Suffix.Percentage -> Suffix
                }

                is Token.Parenthesis -> Group

                else -> Lowest
            }
        }
    }
}