package com.example.calculatorApp.domain

import com.example.calculatorApp.domain.ast.BinaryOperation
import com.example.calculatorApp.domain.ast.EvaluationResult
import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.OperatorBinary

class EngineMathStandard : EngineMath {
    override fun evalBinary(
        leftOperand: Double,
        rightOperand: Double,
        operation: BinaryOperation,
    ): EvaluationResult {
        val normalizedLeft = EvaluationResult.DoubleResult(leftOperand)
        val normalizedRight = EvaluationResult.DoubleResult(rightOperand)
        val result = operation.apply(normalizedLeft, normalizedRight)

        return EvaluationResult.normalizeResult(result.value.toDouble())
    }

    override fun evalPercent(
        operand: Double,
        previousNumber: Double?,
        lastOperator: Operator?
    ): EvaluationResult {
        val percentage = operand / 100

        return when (lastOperator) {
            OperatorBinary.Addition, OperatorBinary.Subtraction -> {
                val result = ((previousNumber ?: 1.0) * (percentage))
                EvaluationResult.normalizeResult(result)
            }

            OperatorBinary.Multiplication, OperatorBinary.Division -> {
                EvaluationResult.normalizeResult(percentage)
            }
            else -> EvaluationResult.normalizeResult(percentage)
        }
    }

    override fun evalSign(operand: Double): EvaluationResult {
        return EvaluationResult.normalizeResult(-operand)
    }
}