package com.example.calculatorApp.domain.action

import com.example.calculatorApp.model.state.CalculatorStateDomain

interface CalculatorActionHandler {

    fun handleAction(action: CalculatorAction, state: CalculatorStateDomain): CalculatorActionHandlerData
}