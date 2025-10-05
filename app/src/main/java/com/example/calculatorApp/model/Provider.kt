package com.example.calculatorApp.model

import com.example.calculatorApp.model.state.CalculatorStateDomain
import com.example.calculatorApp.model.styles.Style

fun interface Provider<T> {
    fun create(state: CalculatorStateDomain, style: Style): T
}