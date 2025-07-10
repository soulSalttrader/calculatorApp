package com.example.calculatorApp.testData

import com.example.calculatorApp.domain.BinaryOperation
import com.example.calculatorApp.domain.ast.EvaluationResult
import com.example.calculatorApp.domain.ast.EvaluationResult.Companion.normalizeResult

data class TestDataEngineMathStandardBinary(
    val leftOperand: Double,
    val rightOperand: Double,
    val operation: BinaryOperation,
) {

    fun expectedResult(operation: BinaryOperation): EvaluationResult {
        return when (operation) {
            ADDITION -> expectedAddition()
            SUBTRACTION -> expectedSubtraction()
            MULTIPLICATION -> expectedMultiplication()
            DIVISION -> expectedDivision()

            else -> throw IllegalArgumentException("Unknown operation: $operation")
        }
    }

    fun expectedAddition() = normalizeResult(leftOperand + rightOperand)
    fun expectedSubtraction() = normalizeResult(leftOperand - rightOperand)
    fun expectedMultiplication() = normalizeResult(leftOperand * rightOperand)

    fun expectedDivision() = normalizeResult(leftOperand / rightOperand)
        .takeUnless { rightOperand == 0.0 } ?: EvaluationResult.DoubleResult(Double.NaN)

    companion object {
        val ADDITION: BinaryOperation = { a, b -> a + b }
        val SUBTRACTION: BinaryOperation = { a, b -> a - b }
        val MULTIPLICATION: BinaryOperation = { a, b -> a * b }
        val DIVISION: BinaryOperation = { a, b -> if (b.value != 0.0) a / b else EvaluationResult.DoubleResult(Double.NaN) }
    }
}