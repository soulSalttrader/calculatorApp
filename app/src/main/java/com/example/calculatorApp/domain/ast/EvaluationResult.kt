package com.example.calculatorApp.domain.ast

sealed interface EvaluationResult : NumericResult {

    data class IntegerResult(override val value: Long) : EvaluationResult
    data class DoubleResult(override val value: Double) : EvaluationResult

    companion object {
        fun normalizeResult(expression: Double) = when (expression % 1.0 == 0.0) {
            true -> IntegerResult(expression.toLong())
            else -> DoubleResult(expression)
        }
    }

    operator fun plus(r: EvaluationResult): EvaluationResult {
        val expression = this.value.toDouble() + r.value.toDouble()

        return normalizeResult(expression)
    }

    operator fun minus(r: EvaluationResult): EvaluationResult {
        val expression = this.value.toDouble() - r.value.toDouble()

        return normalizeResult(expression)
    }

    operator fun times(r: EvaluationResult): EvaluationResult {
        val expression = this.value.toDouble() * r.value.toDouble()

        return normalizeResult(expression)
    }

    operator fun div(r: EvaluationResult): EvaluationResult {
        val expression = this.value.toDouble().safeDiv(r.value.toDouble())

        return normalizeResult(expression)
    }

    private fun Double.safeDiv(right: Double): Double {
        return if (right != 0.0) this / right else Double.NaN
    }
}