package com.example.calculatorApp.model.state

interface HasResult {
    val lastResult: String?
    val cachedOperand: String?
    val isComputed: Boolean
}