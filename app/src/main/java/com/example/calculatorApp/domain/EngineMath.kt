package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic

interface EngineMath : Engine {

    fun applySign(number: Double): Double
    fun applySign(number: Int): Int
    fun applyPercent(operandLeft: Double?, operator: ButtonCalculatorArithmetic?, operandRight: Double): Double
    fun applyArithmetic(operandLeft: Double, operator: ButtonCalculatorArithmetic, operandRight: Double): Double
}