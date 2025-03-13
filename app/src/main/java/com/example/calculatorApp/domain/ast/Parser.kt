package com.example.calculatorApp.domain.ast

interface Parser {
    fun parse(tokens: List<Token>): ASTNode
}