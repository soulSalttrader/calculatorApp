package com.example.calculatorApp.testData

import com.example.calculatorApp.domain.ast.EvaluationResult
import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.OperatorBinary

data class TestDataEngineMathStandardPercent(
    val operand: Double,
    val previousNumber: Double?,
    val lastOperator: Operator?,

) {

    fun expectedZero() = EvaluationResult.IntegerResult(value = 0)

    fun expectedWhole(): Map<TestDataEngineMathStandardPercent, EvaluationResult> = mapOf(
        TestDataEngineMathStandardPercent(-1.0, -1.0, OperatorBinary.Addition) to EvaluationResult.DoubleResult(0.01),
        TestDataEngineMathStandardPercent(-3.0, -3.5, OperatorBinary.Addition) to EvaluationResult.DoubleResult(0.105),
        TestDataEngineMathStandardPercent(-5.0, 5.0, OperatorBinary.Addition) to EvaluationResult.DoubleResult(-0.25),
        TestDataEngineMathStandardPercent(-7.0, 7.5, OperatorBinary.Addition) to EvaluationResult.DoubleResult(-0.525),
        TestDataEngineMathStandardPercent(1.0, -1.0, OperatorBinary.Addition) to EvaluationResult.DoubleResult(-0.01),
        TestDataEngineMathStandardPercent(3.0, -3.5, OperatorBinary.Addition) to EvaluationResult.DoubleResult(-0.105),
        TestDataEngineMathStandardPercent(4.0, 4.0, OperatorBinary.Addition) to EvaluationResult.DoubleResult(0.16),
        TestDataEngineMathStandardPercent(5.0, 5.5, OperatorBinary.Addition) to EvaluationResult.DoubleResult(0.275),
        TestDataEngineMathStandardPercent(7.0, 0.0, OperatorBinary.Addition) to EvaluationResult.IntegerResult(0),
        TestDataEngineMathStandardPercent(-8.0, 0.0, OperatorBinary.Addition) to EvaluationResult.IntegerResult(0),

        TestDataEngineMathStandardPercent(-1.0, -1.0, OperatorBinary.Multiplication) to EvaluationResult.DoubleResult(-0.01),
        TestDataEngineMathStandardPercent(-3.0, -3.5, OperatorBinary.Multiplication) to EvaluationResult.DoubleResult(-0.03),
        TestDataEngineMathStandardPercent(-5.0, 5.0, OperatorBinary.Multiplication) to EvaluationResult.DoubleResult(-0.05),
        TestDataEngineMathStandardPercent(-7.0, 7.5, OperatorBinary.Multiplication) to EvaluationResult.DoubleResult(-0.07),
        TestDataEngineMathStandardPercent(1.0, -1.0, OperatorBinary.Multiplication) to EvaluationResult.DoubleResult(0.01),
        TestDataEngineMathStandardPercent(3.0, -3.5, OperatorBinary.Multiplication) to EvaluationResult.DoubleResult(0.03),
        TestDataEngineMathStandardPercent(4.0, 4.0, OperatorBinary.Multiplication) to EvaluationResult.DoubleResult(0.04),
        TestDataEngineMathStandardPercent(5.0, 5.5, OperatorBinary.Multiplication) to EvaluationResult.DoubleResult(0.05),
        TestDataEngineMathStandardPercent(7.0, 0.0, OperatorBinary.Multiplication) to EvaluationResult.DoubleResult(0.07),
        TestDataEngineMathStandardPercent(-8.0, 0.0, OperatorBinary.Multiplication) to EvaluationResult.DoubleResult(-0.08),
    )

    fun expectedDecimal(): Map<TestDataEngineMathStandardPercent, EvaluationResult> = mapOf(
        TestDataEngineMathStandardPercent(-2.5,-2.0, OperatorBinary.Addition) to EvaluationResult.DoubleResult(0.05),
        TestDataEngineMathStandardPercent(-4.5,-4.5, OperatorBinary.Addition) to EvaluationResult.DoubleResult(0.20249999999999999),
        TestDataEngineMathStandardPercent(-6.5,6.0, OperatorBinary.Addition) to EvaluationResult.DoubleResult(-0.39),
        TestDataEngineMathStandardPercent(-9.5,0.0, OperatorBinary.Addition) to EvaluationResult.IntegerResult(0),
        TestDataEngineMathStandardPercent(2.5,-2.0, OperatorBinary.Addition) to EvaluationResult.DoubleResult(-0.05),
        TestDataEngineMathStandardPercent(6.5,6.0, OperatorBinary.Addition) to EvaluationResult.DoubleResult(0.39),
        TestDataEngineMathStandardPercent(8.5,0.0, OperatorBinary.Addition) to EvaluationResult.IntegerResult(0),

        TestDataEngineMathStandardPercent(-2.5,-2.0, OperatorBinary.Multiplication) to EvaluationResult.DoubleResult(value=-0.025),
        TestDataEngineMathStandardPercent(-4.5,-4.5, OperatorBinary.Multiplication) to EvaluationResult.DoubleResult(value=-0.045),
        TestDataEngineMathStandardPercent(-6.5,6.0, OperatorBinary.Multiplication) to EvaluationResult.DoubleResult(value=-0.065),
        TestDataEngineMathStandardPercent(-9.5,0.0, OperatorBinary.Multiplication) to EvaluationResult.DoubleResult(value=-0.095),
        TestDataEngineMathStandardPercent(2.5,-2.0, OperatorBinary.Multiplication) to EvaluationResult.DoubleResult(value=0.025),
        TestDataEngineMathStandardPercent(6.5,6.0, OperatorBinary.Multiplication) to EvaluationResult.DoubleResult(value=0.065),
        TestDataEngineMathStandardPercent(8.5,0.0, OperatorBinary.Multiplication) to EvaluationResult.DoubleResult(value=0.085),
    )
}