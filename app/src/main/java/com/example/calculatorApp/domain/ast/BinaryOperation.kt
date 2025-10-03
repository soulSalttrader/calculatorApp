package com.example.calculatorApp.domain.ast

fun interface BinaryOperation {
    fun apply(l: EvaluationResult, r: EvaluationResult): EvaluationResult
    operator fun invoke(l: EvaluationResult, r: EvaluationResult) = apply(l, r)
}