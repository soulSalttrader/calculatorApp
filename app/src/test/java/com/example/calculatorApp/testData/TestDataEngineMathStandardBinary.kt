package com.example.calculatorApp.testData

import com.example.calculatorApp.domain.ast.BinaryOperation
import com.example.calculatorApp.domain.ast.BinaryOperations.Add
import com.example.calculatorApp.domain.ast.BinaryOperations.Div
import com.example.calculatorApp.domain.ast.BinaryOperations.Mul
import com.example.calculatorApp.domain.ast.BinaryOperations.Sub
import com.example.calculatorApp.testData.expected.ExpectedEngineMath
import com.example.calculatorApp.testData.expected.ExpectedEngineMathEval
import com.example.calculatorApp.testData.expected.ExpectedEngineMathResult
import com.example.calculatorApp.testData.input.InputEngineMath
import com.example.calculatorApp.testData.input.InputEval

object TestDataEngineMathStandardBinary {

    fun binaryOperationsTest(): Sequence<BinaryOperation> = sequenceOf(Add, Sub, Mul, Div)
    val addTest = ExpectedEngineMathEval { l, r -> ExpectedEngineMathResult.normalizeResultTest(l.value.toDouble() + r.value.toDouble()) }
    val subTest = ExpectedEngineMathEval { l, r -> ExpectedEngineMathResult.normalizeResultTest(l.value.toDouble() - r.value.toDouble()) }
    val mulTest = ExpectedEngineMathEval { l, r -> ExpectedEngineMathResult.normalizeResultTest(l.value.toDouble() * r.value.toDouble()) }

    val divTest = ExpectedEngineMathEval { l, r ->
        val denominator = r.value.toDouble()
        require(denominator != 0.0) { "Division by zero" }
        ExpectedEngineMathResult.normalizeResultTest(l.value.toDouble() / denominator)
    }

    fun engineMathBinaryInput(
        operand: Double,
        previousNumber: Double,
        operation: BinaryOperation,
    ): InputEngineMath.Binary = InputEngineMath.Binary(
        object : InputEval.Binary {
            override val leftOperand: Double = operand
            override val rightOperand: Double = previousNumber
            override val operation: BinaryOperation = operation
        }
    )

    fun engineMathBinaryExpected(
        left: Double,
        right: Double,
        operation: BinaryOperation,
    ): ExpectedEngineMath.Binary {
        val eval = operation.toTestEval()

        val delegate = eval.applyTest(
            ExpectedEngineMathResult.DoubleResultTest(left),
            ExpectedEngineMathResult.DoubleResultTest(right)
        ) as ExpectedEngineMathResult

        return ExpectedEngineMath.Binary(delegate)
    }

    private val operationTestMap: Map<BinaryOperation, ExpectedEngineMathEval> = mapOf(
        Add to addTest, Sub to subTest,
        Mul to mulTest, Div to divTest,
    )

    private fun BinaryOperation.toTestEval(
        mapping: Map<BinaryOperation, ExpectedEngineMathEval> = operationTestMap,
    ): ExpectedEngineMathEval = mapping[this] ?: error("Unknown operation: $this")
}