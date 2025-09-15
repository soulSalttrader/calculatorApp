package com.example.calculatorApp.testData.expected

import com.example.calculatorApp.domain.ast.NumericResult

sealed interface ExpectedEngineMath : Expected {

    data class Binary(
        private val delegateExpected: NumericResult
    ) : ExpectedEngineMath,
        NumericResult by delegateExpected
}