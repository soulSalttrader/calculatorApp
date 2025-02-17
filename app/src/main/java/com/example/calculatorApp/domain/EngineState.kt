package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.state.CalculatorState

interface EngineState : Engine {

    fun handleArithmetic(state: CalculatorState, arithmetic: ButtonCalculatorArithmetic): CalculatorState
    fun applyArithmetic(state: CalculatorState): CalculatorState
    fun enterArithmetic(state: CalculatorState, arithmetic: ButtonCalculatorArithmetic): CalculatorState
    fun applyEquals(state: CalculatorState): CalculatorState

    fun enterNumber(state: CalculatorState, number: Int): CalculatorState
    fun enterDecimal(state: CalculatorState): CalculatorState

    fun applyClear(state: CalculatorState): CalculatorState
    fun applyClearAll(state: CalculatorState): CalculatorState

    fun applySign(state: CalculatorState): CalculatorState
    fun applyPercent(state: CalculatorState): CalculatorState
}