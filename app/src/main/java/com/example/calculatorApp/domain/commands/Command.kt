package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.model.state.CalculatorStateDomain

interface Command {

    fun execute(state: CalculatorStateDomain): CalculatorStateDomain
}