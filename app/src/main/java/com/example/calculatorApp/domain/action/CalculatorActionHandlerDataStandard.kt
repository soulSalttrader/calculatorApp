package com.example.calculatorApp.domain.action

import com.example.calculatorApp.model.state.CalculatorStateDomain

data class CalculatorActionHandlerDataStandard(
    override val newState: CalculatorStateDomain,
    override val shouldResetTextSize: Boolean = false,
) : CalculatorActionHandlerData