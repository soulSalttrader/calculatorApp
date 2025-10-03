package com.example.calculatorApp.domain

import com.example.calculatorApp.domain.ast.BinaryOperation
import com.example.calculatorApp.domain.ast.EvaluationResult
import com.example.calculatorApp.domain.ast.Operator

interface EngineMath : Engine {
    fun evalBinary(leftOperand: Double, rightOperand: Double, operation: BinaryOperation): EvaluationResult
    fun evalPercent(operand: Double, previousNumber: Double?, lastOperator: Operator?): EvaluationResult
    fun evalSign(operand: Number): EvaluationResult
}