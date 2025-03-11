package com.example.calculatorApp.domain.ast

interface Tokenizer {
    fun tokenize(expression: List<String>): List<Token>
}