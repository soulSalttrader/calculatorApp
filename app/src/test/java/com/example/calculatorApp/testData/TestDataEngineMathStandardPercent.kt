package com.example.calculatorApp.testData

import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.testData.TestDataElement.operatorsAllTest
import com.example.calculatorApp.testData.expected.ExpectedEngineMath
import com.example.calculatorApp.testData.expected.ExpectedEngineMathResult
import com.example.calculatorApp.testData.input.InputEngineMath
import com.example.calculatorApp.testData.input.InputEval

object TestDataEngineMathStandardPercent {

    fun engineMathPercentageInput(
        operand: Number,
        previousNumber: Number?,
        lastOperator: Operator?,
    ): InputEngineMath.Percentage = InputEngineMath.Percentage(
        object : InputEval.Percentage {
            override val operand: Double = operand.toDouble()
            override val lastOperator: Operator? = lastOperator
            override val previousNumber: Double? = previousNumber?.toDouble()
        }
    )

    fun engineMathPercentageExpected(
        operand: Number,
        previousNumber: Number?,
        lastOperator: Operator?,
    ): ExpectedEngineMath.Percentage = ExpectedEngineMath.Percentage(
        ExpectedEngineMathResult.evalPercentTest(operand, previousNumber, lastOperator)
    )

    // Skipping Subtraction and Division to avoid redundant test cases.
    // Addition and Multiplication cover the same result patterns,
    // making Subtraction and Division unnecessary for efficiency.
    val testOperators = operatorsAllTest
        .filter { it != OperatorBinary.Subtraction && it != OperatorBinary.Division }
}