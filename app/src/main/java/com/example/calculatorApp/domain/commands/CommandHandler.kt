package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.state.CalculatorState

data class CommandHandler<T : Button>(
    private val engineState: EngineState,
    private val button: T
) : Command {

    override fun execute(state: CalculatorState): CalculatorState {
        return when (button) {
            is ButtonCalculatorBinary -> engineState.handleBinary(state, button)
            is ButtonCalculatorUnary -> engineState.handleUnary(state, button)
            is ButtonCalculatorControl -> engineState.handleControl(state, button)
            is ButtonCalculatorNumber -> engineState.handleNumber(state, button)
            else -> throw IllegalArgumentException("Invalid button: $button")
        }
    }
}