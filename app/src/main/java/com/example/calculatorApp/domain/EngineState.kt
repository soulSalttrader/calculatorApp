package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.state.CalculatorState

interface EngineState : Engine {

    fun enterOperation(state: CalculatorState, operation: ButtonCalculatorArithmetic): CalculatorState
    fun addNumber(state: CalculatorState, number: Int): CalculatorState
    fun enterDecimal(state: CalculatorState): CalculatorState
    fun clear(state: CalculatorState): CalculatorState
    fun clearAll(): CalculatorState
}