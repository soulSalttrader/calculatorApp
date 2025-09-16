package com.example.calculatorApp.arguments

import com.example.calculatorApp.domain.ast.BinaryOperation
import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataElement.operatorsAllTest
import com.example.calculatorApp.testData.TestDataEngineMathStandardBinary.binaryOperationsTest
import com.example.calculatorApp.testData.TestDataEngineMathStandardBinary.engineMathBinaryExpected
import com.example.calculatorApp.testData.TestDataEngineMathStandardBinary.engineMathBinaryInput
import com.example.calculatorApp.testData.TestDataEngineMathStandardBinary.provideOperandsTest
import com.example.calculatorApp.testData.TestDataEngineMathStandardPercent
import com.example.calculatorApp.testData.TestDataEngineMathStandardSign.engineMathSignExpected
import com.example.calculatorApp.testData.TestDataEngineMathStandardSign.engineMathSignInput
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedEngineMath
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputEngineMath

object TestArgumentsEngineMath {

    fun provideEngineMathBinaryTestCases(
        operations: Sequence<BinaryOperation> = binaryOperationsTest(),
        operands: Sequence<Pair<Double, Double>> = testOperands,
        factoryInput: (Double, Double, BinaryOperation) -> InputEngineMath.Binary = ::engineMathBinaryInput,
        factoryExpected: (Double, Double, BinaryOperation) -> ExpectedEngineMath.Binary = ::engineMathBinaryExpected
    ): Sequence<TestCase<Input, Expected>> =
        sequence {
            operations.forEach { operation ->
                operands.forEach { (operand, previousNumber) ->
                    yield(
                        TestCase(
                            input = factoryInput(operand, previousNumber, operation),
                            expected = factoryExpected(operand, previousNumber, operation)
                        )
                    )
                }
            }
        }

    fun providePercent(
        operators: Sequence<Operator> = testOperators,
        operands: Sequence<Pair<Double, Double>> = testOperands,
    ): Sequence<TestDataEngineMathStandardPercent> =
        sequence {
            operators.forEach { operator ->
                operands.forEach { (operand, previousNumber) ->
                    yield(TestDataEngineMathStandardPercent(operand, previousNumber, operator))
                }
            }
        }

    fun provideEngineMathSignTestCases(
        operands: Sequence<Pair<Number, Number>> = provideOperandsTest(),
        factoryInput: (Number) -> InputEngineMath.Sign = ::engineMathSignInput,
        factoryExpected: (Number) -> ExpectedEngineMath.Sign = ::engineMathSignExpected,
    ): Sequence<TestCase<Input, Expected>> =
        sequence {
            operands.forEach { (operand, _) ->
                yield(
                    TestCase(
                        factoryInput(operand),
                        factoryExpected(operand)
                    )
                )
            }
        }

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