package com.example.calculatorApp.domain.action

import com.example.calculatorApp.model.state.CalculatorState

interface CalculatorActionHandler {

    fun handleAction(action: CalculatorAction, state: CalculatorState): CalculatorActionHandlerData
}