package com.example.calculatorApp.domain.ast

import com.example.calculatorApp.domain.ast.EvaluationResult.Companion.normalizeResult
import kotlin.text.toDouble

object BinaryOperations {


    object Add : BinaryOperation {
        override fun apply(l: EvaluationResult, r: EvaluationResult) =
            normalizeResult(l.value.toDouble() + r.value.toDouble())

        override fun toString(): String = "Add"
    }

    object Sub : BinaryOperation {
        override fun apply(l: EvaluationResult, r: EvaluationResult) =
            normalizeResult(l.value.toDouble() - r.value.toDouble())

        override fun toString(): String = "Sub"
    }

    object Mul : BinaryOperation {
        override fun apply(l: EvaluationResult, r: EvaluationResult) =
            normalizeResult(l.value.toDouble() * r.value.toDouble())

        override fun toString(): String = "Mul"
    }

    object Div : BinaryOperation {
        override fun apply(l: EvaluationResult, r: EvaluationResult): EvaluationResult {
            val denominator = r.value.toDouble()
            require(denominator != 0.0) { Double.NaN }
            return normalizeResult(l.value.toDouble() / denominator)
        }

        override fun toString(): String = "Div"
    }
}