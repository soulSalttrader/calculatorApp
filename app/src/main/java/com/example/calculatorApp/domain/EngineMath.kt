package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic

interface EngineMath : Engine {

    fun applySign(number: Double): Double
    fun applyPercent(operandLeft: Double?, operandRight: Double): Double
    fun applyArithmetic(operandLeft: Double, operator: ButtonCalculatorArithmetic, operandRight: Double): Double
}