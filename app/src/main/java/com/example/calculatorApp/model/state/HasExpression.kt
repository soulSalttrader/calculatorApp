package com.example.calculatorApp.model.state

import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.Token

interface HasExpression {
    val expression: List<Token>
    val lastOperand: String
    val lastOperator: Operator?
}