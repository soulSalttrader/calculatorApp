package com.example.calculatorApp.testData.expected

import com.example.calculatorApp.domain.ast.NumericResult

interface ExpectedEngineMathResult : NumericResult {
    data class IntegerResultTest(override val value: Long) : ExpectedEngineMathResult
    data class DoubleResultTest(override val value: Double) : ExpectedEngineMathResult

    companion object {
        fun normalizeResultTest(n: Number): ExpectedEngineMathResult =
            when (n) {
                is Long -> IntegerResultTest(n)
                is Double -> if (n % 1.0 == 0.0) IntegerResultTest(n.toLong()) else DoubleResultTest(n)
                else -> error("Unsupported type: ${n::class}")
            }
    }
}