package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary

interface EngineMath : Engine {

    fun applySign(number: Double): Double
    fun applySign(number: Int): Int
    fun applyPercent(operandLeft: Double?, operator: ButtonCalculatorBinary?, operandRight: Double): Double
    fun applyArithmetic(operandLeft: Double, operator: ButtonCalculatorBinary, operandRight: Double): Double
}