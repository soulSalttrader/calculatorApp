package com.example.calculatorApp.domain.ast

import com.example.calculatorApp.domain.ast.EvaluationResult.Companion.normalizeResult

object BinaryOperations {

    val Add = BinaryOperation { l, r -> normalizeResult(l.value.toDouble() + r.value.toDouble()) }
    val Sub = BinaryOperation { l, r -> normalizeResult(l.value.toDouble() - r.value.toDouble()) }
    val Mul = BinaryOperation { l, r -> normalizeResult(l.value.toDouble() * r.value.toDouble()) }

    val Div = BinaryOperation { l, r ->
        val denominator = r.value.toDouble()
        require(denominator != 0.0) { Double.NaN }
        normalizeResult(l.value.toDouble() / denominator)
    }
}