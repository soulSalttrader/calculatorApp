package com.example.calculatorApp.domain

import com.example.calculatorApp.domain.ast.ASTNode
import com.example.calculatorApp.domain.ast.BinaryOperations
import com.example.calculatorApp.domain.ast.EvaluationResult
import com.example.calculatorApp.domain.ast.EvaluationResult.Companion.normalizeResult
import com.example.calculatorApp.domain.ast.OperatorBinary

class EngineNodeStandard(private val engineMath: EngineMath) : EngineNode {

    override fun evaluate(astNode: ASTNode): EvaluationResult {
        return when (astNode) {
            is ASTNode.Number -> normalizeResult(astNode.value)
            is ASTNode.Binary -> evalBinaryExpression(astNode)
        }
    }

    private fun evalBinaryExpression(astNode: ASTNode.Binary): EvaluationResult {
        val left = evaluate(astNode.left).value.toDouble()
        val right = evaluate(astNode.right).value.toDouble()

        val operation = when (astNode.operator) {
            is OperatorBinary.Addition       -> BinaryOperations.Add
            is OperatorBinary.Subtraction    -> BinaryOperations.Sub
            is OperatorBinary.Multiplication -> BinaryOperations.Mul
            is OperatorBinary.Division       -> BinaryOperations.Div
        }

        return engineMath.evalBinary(left, right, operation)
    }
}