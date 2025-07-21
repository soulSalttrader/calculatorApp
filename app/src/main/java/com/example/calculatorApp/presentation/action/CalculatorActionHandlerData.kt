package com.example.calculatorApp.presentation.action

import com.example.calculatorApp.model.state.CalculatorState

interface CalculatorActionHandlerData {
    val newState: CalculatorState
    val shouldResetTextSize: Boolean
}