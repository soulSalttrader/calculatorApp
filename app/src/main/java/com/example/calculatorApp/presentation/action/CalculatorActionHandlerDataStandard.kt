package com.example.calculatorApp.presentation.action

import com.example.calculatorApp.model.state.CalculatorState

data class CalculatorActionHandlerDataStandard(
    override val newState: CalculatorState,
    override val shouldResetTextSize: Boolean = false,
) : CalculatorActionHandlerData