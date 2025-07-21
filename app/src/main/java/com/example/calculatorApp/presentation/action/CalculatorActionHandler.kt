package com.example.calculatorApp.presentation.action

import com.example.calculatorApp.domain.actions.CalculatorAction
import com.example.calculatorApp.model.state.CalculatorState

interface CalculatorActionHandler {

    fun handleAction(action: CalculatorAction, state: CalculatorState): CalculatorActionHandlerData
}