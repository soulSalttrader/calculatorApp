package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.state.CalculatorState

interface EngineState : Engine {

    fun enterOperation(state: CalculatorState, operation: ButtonCalculatorArithmetic): CalculatorState
    fun enterNumber(state: CalculatorState, number: Int): CalculatorState
    fun enterDecimal(state: CalculatorState): CalculatorState
    fun applyClear(state: CalculatorState): CalculatorState
    fun applyClearAll(state: CalculatorState): CalculatorState
}