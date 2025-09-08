package com.example.calculatorApp.arguments

import com.example.calculatorApp.testData.TestDataEngineMathStandardBinary
import com.example.calculatorApp.testData.TestDataEngineMathStandardPercent
import com.example.calculatorApp.testData.TestDataEngineMathStandardSign
import com.example.calculatorApp.domain.BinaryOperation
import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.testData.TestDataElement.operatorsAllTest

object TestArgumentsEngineMath {

    fun provideBinary(
        operations: Sequence<BinaryOperation> = binaryOperations,
        operands: Sequence<Pair<Double, Double>> = testOperands,
    ): Sequence<TestDataEngineMathStandardBinary>  =
        sequence {
            operations.forEach { operation ->
                operands.forEach { (operand, previousNumber) ->
                    yield(TestDataEngineMathStandardBinary(operand, previousNumber, operation))
                }
            }
        }


    fun providePercent(
        operators: Sequence<Operator> = testOperators,
        operands: Sequence<Pair<Double, Double>> = testOperands,
    ) : Sequence<TestDataEngineMathStandardPercent>  =
        sequence {
            operators.forEach { operator ->
                operands.forEach { (operand, previousNumber) ->
                    yield(TestDataEngineMathStandardPercent(operand, previousNumber, operator))
                }
            }
        }


    fun provideSign(
        operands: Sequence<Double> = testOperands.map { it.first }.distinct(),
    ): Sequence<TestDataEngineMathStandardSign>  =
        sequence {
            operands.forEach { operator ->
                yield(TestDataEngineMathStandardSign(operator))
            }
        }


    private val binaryOperations: Sequence<BinaryOperation> = sequenceOf(
        TestDataEngineMathStandardBinary.ADDITION,
        TestDataEngineMathStandardBinary.SUBTRACTION,
        TestDataEngineMathStandardBinary.MULTIPLICATION,
        TestDataEngineMathStandardBinary.DIVISION,
    )

    // Skipping Subtraction and Division to avoid redundant test cases.
    // Addition and Multiplication cover the same result patterns,
    // making Subtraction and Division unnecessary for efficiency.
    private val testOperators = operatorsAllTest
        .filter { it != OperatorBinary.Subtraction && it != OperatorBinary.Division }

    private val testOperands = sequenceOf(
        -1.0 to -1.0,
        -2.5 to -2.0,
        -3.0 to -3.5,
        -4.5 to -4.5,
        -5.0 to 5.0,
        -6.5 to 6.0,
        -7.0 to 7.5,
        -8.0 to 0.0,
        -9.5 to 0.0,

        0.0 to 0.0,
        0.0 to -5.0,
        0.0 to 5.0,
        0.0 to -5.5,
        0.0 to 5.5,

        1.0 to -1.0,
        2.5 to -2.0,
        3.0 to -3.5,
        4.0 to 4.0,
        5.0 to 5.5,
        6.5 to 6.0,
        7.0 to 0.0,
        8.5 to 0.0,
    )
}