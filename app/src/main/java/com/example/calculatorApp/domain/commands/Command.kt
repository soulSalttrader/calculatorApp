package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.model.state.CalculatorState

interface Command {

    fun execute(state: CalculatorState): CalculatorState
}