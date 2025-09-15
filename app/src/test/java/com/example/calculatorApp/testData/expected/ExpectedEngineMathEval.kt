package com.example.calculatorApp.testData.expected

import com.example.calculatorApp.domain.ast.NumericResult

fun interface ExpectedEngineMathEval {
    fun applyTest(a: NumericResult, b: NumericResult): NumericResult
}