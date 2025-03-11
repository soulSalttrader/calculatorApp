package com.example.calculatorApp.domain.ast

import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorParenthesis
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary

object TokenizerUtils {

    fun String.isNumber(): Boolean = this.toDoubleOrNull() != null
    fun String.isBinary(): Boolean {
        return OperatorBinary::class.sealedSubclasses
            .mapNotNull { it.objectInstance }
            .any { it.symbol.label == this }
    }
    fun String.isUnaryPrefix(): Boolean {
        return OperatorUnary.Prefix::class.sealedSubclasses
            .mapNotNull { it.objectInstance }
            .any { it.symbol.label == this }
    }

    fun String.isUnarySuffix(): Boolean {
        return OperatorUnary.Suffix::class.sealedSubclasses
            .mapNotNull { it.objectInstance }
            .any { it.symbol.label == this }
    }

    fun String.isParenthesis(): Boolean {
        return OperatorParenthesis::class.sealedSubclasses
            .mapNotNull { it.objectInstance }
            .any { it.symbol.label == this }
    }

    fun ButtonCalculatorBinary.toBinaryOperator(): OperatorBinary = when (this) {
        ButtonCalculatorBinary.Addition -> OperatorBinary.Addition
        ButtonCalculatorBinary.Subtraction -> OperatorBinary.Subtraction
        ButtonCalculatorBinary.Multiplication -> OperatorBinary.Multiplication
        ButtonCalculatorBinary.Division -> OperatorBinary.Division
    }

    fun String.toBinaryOperator(): OperatorBinary {
        val matchingButton = ButtonCalculatorBinary::class.sealedSubclasses
            .mapNotNull { it.objectInstance }
            .firstOrNull { it.symbol.label == this }

        return matchingButton?.toBinaryOperator()
            ?: throw IllegalArgumentException("Unknown binary operator: $this.")
    }

    fun ButtonCalculatorUnary.toUnaryOperator(): OperatorUnary = when (this) {
        ButtonCalculatorUnary.Sign -> OperatorUnary.Prefix.Sign
        ButtonCalculatorUnary.Percentage -> OperatorUnary.Suffix.Percentage
    }

    fun String.toUnaryOperator(): OperatorUnary {
        val matchingButton = ButtonCalculatorUnary::class.sealedSubclasses
            .mapNotNull { it.objectInstance }
            .firstOrNull { it.symbol.label == this }

        return matchingButton?.toUnaryOperator()
            ?: throw IllegalArgumentException("Unknown unary operator: $this.")
    }

    fun String.toParenthesisOperator(): OperatorParenthesis = when (this) {
        ButtonCalculatorParenthesis.OpenParenthesis.symbol.label -> OperatorParenthesis.Open
        ButtonCalculatorParenthesis.CloseParenthesis.symbol.label -> OperatorParenthesis.Close
        else -> throw IllegalArgumentException("Unknown parenthesis: $this.")
    }
}