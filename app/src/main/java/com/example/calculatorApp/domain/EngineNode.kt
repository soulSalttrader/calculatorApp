package com.example.calculatorApp.domain

import com.example.calculatorApp.domain.ast.ASTNode
import com.example.calculatorApp.domain.ast.EvaluationResult

interface EngineNode : Engine {
    fun evaluate(astNode: ASTNode): EvaluationResult
}