package com.example.calculatorApp.domain

sealed class ASTNode {

    data class Number(val value: Double) : ASTNode()
    data class Binary(val operator: OperatorBinary, val left: ASTNode, val right: ASTNode) : ASTNode()
}