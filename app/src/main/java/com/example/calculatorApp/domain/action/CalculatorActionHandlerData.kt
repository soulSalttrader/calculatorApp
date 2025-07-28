package com.example.calculatorApp.domain.action

import com.example.calculatorApp.model.state.CalculatorStateDomain

interface CalculatorActionHandlerData {
    val newState: CalculatorStateDomain
    val shouldResetTextSize: Boolean
}