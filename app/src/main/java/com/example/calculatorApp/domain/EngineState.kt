package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.state.CalculatorState

interface EngineState : Engine {
    fun handleBinary(state: CalculatorState, binary: ButtonCalculatorBinary): CalculatorState
    fun handleUnary(state: CalculatorState, unary: ButtonCalculatorUnary): CalculatorState
    fun handleControl(state: CalculatorState, control: ButtonCalculatorControl): CalculatorState
    fun handleNumber(state: CalculatorState, number: ButtonCalculatorNumber): CalculatorState
}