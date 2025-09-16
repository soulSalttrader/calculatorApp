package com.example.calculatorApp.arguments

import com.example.calculatorApp.domain.ast.BinaryOperation
import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataEngineMathStandardBinary.binaryOperationsTest
import com.example.calculatorApp.testData.TestDataEngineMathStandardBinary.engineMathBinaryExpected
import com.example.calculatorApp.testData.TestDataEngineMathStandardBinary.engineMathBinaryInput
import com.example.calculatorApp.testData.TestDataEngineMathStandardBinary.provideOperandsTest
import com.example.calculatorApp.testData.TestDataEngineMathStandardPercent.engineMathPercentageExpected
import com.example.calculatorApp.testData.TestDataEngineMathStandardPercent.engineMathPercentageInput
import com.example.calculatorApp.testData.TestDataEngineMathStandardPercent.testOperators
import com.example.calculatorApp.testData.TestDataEngineMathStandardSign.engineMathSignExpected
import com.example.calculatorApp.testData.TestDataEngineMathStandardSign.engineMathSignInput
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedEngineMath
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputEngineMath

object TestArgumentsEngineMath {

    fun provideEngineMathBinaryTestCases(
        operations: Sequence<BinaryOperation> = binaryOperationsTest(),
        operands: Sequence<Pair<Number, Number>> = provideOperandsTest(),
        factoryInput: (Number, Number, BinaryOperation) -> InputEngineMath.Binary = ::engineMathBinaryInput,
        factoryExpected: (Number, Number, BinaryOperation) -> ExpectedEngineMath.Binary = ::engineMathBinaryExpected
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

    fun provideEngineMathPercentageTestCases(
        operators: Sequence<Operator> = testOperators,
        operands: Sequence<Pair<Number, Number>> = provideOperandsTest(),
        factoryInput: (Number, Number, Operator?) -> InputEngineMath.Percentage = ::engineMathPercentageInput,
        factoryExpected: (Number, Number, Operator?) -> ExpectedEngineMath.Percentage = ::engineMathPercentageExpected,
    ): Sequence<TestCase<Input, Expected>> =
        sequence {
            operators.forEach { operator ->
                operands.forEach { (operand, previousNumber) ->
                    yield(
                        TestCase(
                            factoryInput(operand, previousNumber, operator),
                            factoryExpected(operand, previousNumber, operator)
                        )
                    )
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
}