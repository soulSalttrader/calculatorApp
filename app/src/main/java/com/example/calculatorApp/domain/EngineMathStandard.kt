package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary

class EngineMathStandard : EngineMath {

    override fun applySign(number: Double): Double = -number
    override fun applySign(number: Int): Int = -number

    override fun applyPercent(
        operandLeft: Double?,
        operator: ButtonCalculatorBinary?,
        operandRight: Double
    ): Double {
        return when (operator) {
            ButtonCalculatorBinary.Addition,
            ButtonCalculatorBinary.Subtraction -> (operandLeft ?: 1.0) * (operandRight / 100)

            ButtonCalculatorBinary.Multiplication,
            ButtonCalculatorBinary.Division -> operandRight / 100

            else -> operandRight / 100
        }
    }

    override fun applyArithmetic(
        operandLeft: Double,
        operator: ButtonCalculatorBinary,
        operandRight: Double,
    ): Double {

        return  when (operator) {
            is ButtonCalculatorBinary.Addition -> operandLeft + operandRight
            is ButtonCalculatorBinary.Subtraction -> operandLeft - operandRight
            is ButtonCalculatorBinary.Multiplication -> operandLeft * operandRight
            is ButtonCalculatorBinary.Division -> safeDivide(operandLeft, operandRight)
        }
    }

    private fun safeDivide(operandLeft: Double, operandRight: Double) = if (operandRight != 0.0) operandLeft / operandRight else Double.NaN
}