package com.example.calculatorApp.testData.expected

import com.example.calculatorApp.domain.ast.NumericResult

sealed interface ExpectedEngineMath : Expected {

    data class Binary(
        private val delegateExpected: NumericResult
    ) : ExpectedEngineMath,
        NumericResult by delegateExpected

    data class Sign(
        private val delegateExpected: NumericResult
    ) : ExpectedEngineMath,
        NumericResult by delegateExpected

    data class Percentage(
        private val delegateExpected: NumericResult
    ) : ExpectedEngineMath,
        NumericResult by delegateExpected
}