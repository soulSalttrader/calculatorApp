package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.state.CalculatorStateDomain

interface EngineState : Engine {
    fun handleBinary(state: CalculatorStateDomain, binary: ButtonCalculatorBinary): CalculatorStateDomain
    fun handleUnary(state: CalculatorStateDomain, unary: ButtonCalculatorUnary): CalculatorStateDomain
    fun handleControl(state: CalculatorStateDomain, control: ButtonCalculatorControl): CalculatorStateDomain
    fun handleNumber(state: CalculatorStateDomain, number: ButtonCalculatorNumber): CalculatorStateDomain
}