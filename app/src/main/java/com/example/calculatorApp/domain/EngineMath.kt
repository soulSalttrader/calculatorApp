package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic

interface EngineMath : Engine {

    fun applySign(number: Double): Double
    fun applyPercent(number: Double): Double
    fun applyArithmetic(left: Double, right: Double, arithmetic: ButtonCalculatorArithmetic): Double
}