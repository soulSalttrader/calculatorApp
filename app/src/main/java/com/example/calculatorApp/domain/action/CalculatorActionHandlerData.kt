package com.example.calculatorApp.domain.action

import com.example.calculatorApp.model.state.CalculatorState

interface CalculatorActionHandlerData {
    val newState: CalculatorState
    val shouldResetTextSize: Boolean
}