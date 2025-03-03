package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.state.CalculatorState

interface EngineState : Engine {

    fun handleArithmetic(state: CalculatorState, arithmetic: ButtonCalculatorBinary): CalculatorState
    fun handleControl(state: CalculatorState, control: ButtonCalculatorControl): CalculatorState
    fun enterNumber(state: CalculatorState, number: Int): CalculatorState

    fun applyArithmetic(state: CalculatorState): CalculatorState
    fun enterArithmetic(state: CalculatorState, arithmetic: ButtonCalculatorBinary): CalculatorState

    fun applySign(state: CalculatorState): CalculatorState
    fun applyPercent(state: CalculatorState): CalculatorState
}