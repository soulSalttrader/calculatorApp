package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic

class EngineMathStandard : EngineMath {

    override fun applySign(number: Double): Double = -number
    override fun applyPercent(number: Double): Double = number / 100

    override fun applyArithmetic(
        left: Double,
        right: Double,
        arithmetic: ButtonCalculatorArithmetic,
    ): Double {

        return  when (arithmetic) {
            is ButtonCalculatorArithmetic.Addition -> left + right
            is ButtonCalculatorArithmetic.Subtraction -> left - right
            is ButtonCalculatorArithmetic.Multiplication -> left * right
            is ButtonCalculatorArithmetic.Division -> safeDivide(right, left)
            else -> throw IllegalArgumentException("Unknown operation.")
        }
    }

    private fun safeDivide(right: Double, left: Double) = if (right != 0.0) left / right else Double.NaN
}