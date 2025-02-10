package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic

interface EngineMath : Engine {

    fun plusMinus(number: Double): Double
    fun convertToPercent(number: Double): Double
    fun calculateArithmeticOperations(left: Double, right: Double, operation: ButtonCalculatorArithmetic): Double
}