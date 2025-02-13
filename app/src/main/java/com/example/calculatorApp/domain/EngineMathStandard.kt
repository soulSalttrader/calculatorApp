package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic

class EngineMathStandard : EngineMath {

    override fun applySign(number: Double): Double = -number
    override fun applyPercent(number: Double): Double = number / 100

    override fun applyArithmetic(
        operandLeft: Double,
        operator: ButtonCalculatorArithmetic,
        operandRight: Double,
    ): Double {

        return  when (operator) {
            is ButtonCalculatorArithmetic.Addition -> operandLeft + operandRight
            is ButtonCalculatorArithmetic.Subtraction -> operandLeft - operandRight
            is ButtonCalculatorArithmetic.Multiplication -> operandLeft * operandRight
            is ButtonCalculatorArithmetic.Division -> safeDivide(operandLeft, operandRight)
            else -> throw IllegalArgumentException("Unknown operation.")
        }
    }

    private fun safeDivide(operandLeft: Double, operandRight: Double) = if (operandRight != 0.0) operandLeft / operandRight else Double.NaN
}