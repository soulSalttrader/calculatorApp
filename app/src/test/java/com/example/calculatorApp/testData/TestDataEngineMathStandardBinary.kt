package com.example.calculatorApp.testData

import com.example.calculatorApp.domain.ast.BinaryOperation
import com.example.calculatorApp.domain.ast.BinaryOperations
import com.example.calculatorApp.testData.expected.ExpectedEngineMathEval
import com.example.calculatorApp.testData.expected.ExpectedEngineMathResult

object TestDataEngineMathStandardBinary {

     fun binaryOperationsTest(): Sequence<BinaryOperation> = sequenceOf(
        BinaryOperations.Add,
        BinaryOperations.Sub,
        BinaryOperations.Mul,
        BinaryOperations.Div,
    )

    val addTest = ExpectedEngineMathEval { l, r -> ExpectedEngineMathResult.normalizeResultTest(l.value.toDouble() + r.value.toDouble()) }
    val subTest = ExpectedEngineMathEval { l, r -> ExpectedEngineMathResult.normalizeResultTest(l.value.toDouble() - r.value.toDouble()) }
    val mulTest = ExpectedEngineMathEval { l, r -> ExpectedEngineMathResult.normalizeResultTest(l.value.toDouble() * r.value.toDouble()) }

    val divTest = ExpectedEngineMathEval { l, r ->
        val denominator = r.value.toDouble()
        require(denominator != 0.0) { "Division by zero" }
        ExpectedEngineMathResult.normalizeResultTest(l.value.toDouble() / denominator)
    }
}