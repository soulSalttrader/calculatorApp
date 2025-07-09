package com.example.calculatorApp.domain

import com.example.calculatorApp.domain.ast.ASTNode
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
        return when (astNode.operator) {
            is OperatorBinary.Addition -> engineMath.evalBinary(left, right) { l, r -> l + r }
            is OperatorBinary.Subtraction -> engineMath.evalBinary(left, right) { l, r -> l - r }
            is OperatorBinary.Multiplication -> engineMath.evalBinary(left, right) { l, r -> l * r }
            is OperatorBinary.Division -> engineMath.evalBinary(left, right) { l, r -> l / r }
        }
    }
}