package com.example.calculatorApp.domain.ast

sealed interface EvaluationResult : NumericResult {

    data class IntegerResult(override val value: Long) : EvaluationResult
    data class DoubleResult(override val value: Double) : EvaluationResult

    companion object {
        fun normalizeResult(expression: Double) = when (expression % 1.0 == 0.0 && expression != 0.0) {
            true -> IntegerResult(expression.toLong())
            else -> DoubleResult(expression)
        }
    }
}