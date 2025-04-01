package com.example.calculatorApp.domain

import com.example.calculatorApp.domain.ast.EvaluationResult
import com.example.calculatorApp.domain.ast.Operator

typealias BinaryOperation = (EvaluationResult, EvaluationResult) -> EvaluationResult

interface EngineMath : Engine {
    fun evalBinary(leftOperand: Double, rightOperand: Double, operation: BinaryOperation): EvaluationResult
    fun evalPercent(operand: Double, previousNumber: Double?, lastOperator: Operator?): EvaluationResult
    fun evalSign(operand: Double): EvaluationResult
}