package com.example.calculatorApp.domain.ast

sealed interface EvaluationResult : NumericResult {

    data class IntegerResult(override val value: Long) : EvaluationResult
    data class DoubleResult(override val value: Double) : EvaluationResult

    companion object {
        fun normalizeResult(number: Number) = when (number) {
            is Int, is Long -> IntegerResult(number.toLong())
            is Double, is Float -> DoubleResult(number.toDouble())
            else -> throw IllegalArgumentException("Unsupported type: ${number::class}")
        }
    }
}