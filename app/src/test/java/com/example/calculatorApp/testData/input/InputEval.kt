package com.example.calculatorApp.testData.input

import com.example.calculatorApp.domain.ast.BinaryOperation
import com.example.calculatorApp.domain.ast.Operator

sealed interface InputEval {

    interface Binary : InputEval {
        val leftOperand: Double
        val rightOperand: Double
        val operation: BinaryOperation
    }

    interface Percentage : InputEval {
        val operand: Double
        val previousNumber: Double?
        val lastOperator: Operator?
    }

    interface Sign : InputEval {
        val operand: Double
    }
}