package com.example.calculatorApp.testData.expected

import com.example.calculatorApp.domain.ast.NumericResult
import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.OperatorBinary

interface ExpectedEngineMathResult : NumericResult {
    data class IntegerResultTest(override val value: Long) : ExpectedEngineMathResult
    data class DoubleResultTest(override val value: Double) : ExpectedEngineMathResult

    companion object {
        fun normalizeResultTest(n: Number): ExpectedEngineMathResult = when (n) {
            is Int, is Long -> IntegerResultTest(n.toLong())
            is Double, is Float -> DoubleResultTest(n.toDouble())
            else -> throw IllegalArgumentException("Unsupported type: ${n::class}")
        }

        fun evalPercentTest(
            operand: Number,
            previousNumber: Number?,
            lastOperator: Operator?
        ): ExpectedEngineMathResult {
            val percentage = operand.toDouble() / 100
            val value = expectedPercentValue(lastOperator, previousNumber, percentage)
            return normalizeResultTest(value)
        }

        private fun expectedPercentValue(
            lastOperator: Operator?,
            previousNumber: Number?,
            percentage: Number
        ): Double {
            val value = when (lastOperator) {
                OperatorBinary.Addition,
                OperatorBinary.Subtraction -> (previousNumber ?: 1.0).toDouble() * percentage.toDouble()
                else                       -> percentage
            }
            return value.toDouble()
        }
    }
}