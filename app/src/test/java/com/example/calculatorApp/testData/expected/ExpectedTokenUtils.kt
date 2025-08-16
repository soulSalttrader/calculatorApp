package com.example.calculatorApp.testData.expected

import com.example.calculatorApp.domain.ast.Operator

data class ExpectedTokenUtils(
    val operator: Operator? = null,
    val matches: Boolean? = null,
) : Expected