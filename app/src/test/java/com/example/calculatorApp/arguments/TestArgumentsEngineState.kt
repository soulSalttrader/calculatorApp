package com.example.calculatorApp.arguments

import com.example.calculatorApp.arguments.builder.ArgumentsBuilder
import com.example.calculatorApp.arguments.builder.ArgumentsBuilderEngineState
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.scenario.ScenarioEngineState

object TestArgumentsEngineState : TestArguments {

    fun provideEngineStateBinaryTestCases(
        scenario: ScenarioEngineState,
        builder: ArgumentsBuilder<Input, Expected> = ArgumentsBuilderEngineState()
    ): Sequence<TestCase<Input, Expected>> =
        builder.provideTestCases(scenario)

//    // Skipping Subtraction and Division to avoid redundant test cases.
//    // Addition and Multiplication cover the same result patterns,
//    // making Subtraction and Division unnecessary for efficiency.
//    fun provideStateUnary(
//        lastOperands: Sequence<String> = testOperands,
//        buttonsBinary: Sequence<Button> = buttonsBinaryTest
//            .filter { it == ButtonCalculatorBinary.Addition || it == ButtonCalculatorBinary.Multiplication },
//        buttonsUnary: Sequence<Button> = buttonsUnaryTest,
//    ): Sequence<TestDataEngineStateStandard> =
//        sequence {
//            buttonsUnary.forEach { buttonUnary ->
//                lastOperands.forEach { lastOperand ->
//                    buttonsBinary.forEach { buttonBinary ->
//                        yield(
//                            TestDataEngineStateStandard(
//                                lastOperand = lastOperand,
//                                buttonUnary = buttonUnary,
//                                buttonBinary = buttonBinary,
//                            )
//                        )
//                    }
//                }
//            }
//        }
//
//    fun provideStateControl(
//        lastOperands: Sequence<String> = testOperands,
//        buttonsBinary: Sequence<Button> = buttonsBinaryTest
//            .filter { it == ButtonCalculatorBinary.Addition || it == ButtonCalculatorBinary.Division },
//    ): Sequence<TestDataEngineStateStandard> =
//        sequence {
//            buttonsBinary.forEach { button ->
//                lastOperands.forEach { lastOperand ->
//                    yield(
//                        TestDataEngineStateStandard(
//                            expression = listOf(
//                                Token.Number(lastOperand.toDouble()),
//                                Token.Binary(button.toOperator() as OperatorBinary),
//                            ),
//                            lastOperand = lastOperand,
//                            buttonBinary = button,
//                            buttonUnary = ButtonCalculatorUnary.Percentage,
//                        )
//                    )
//                }
//            }
//        }
//
//    fun provideStateNumber(
//        lastOperands: Sequence<String> = testOperands,
//        buttonsBinary: Sequence<Button> = buttonsBinaryTest,
//    ): Sequence<TestDataEngineStateStandard> =
//        sequence {
//            buttonsBinary.forEach { button ->
//                lastOperands.forEach { lastOperand ->
//                    yield(
//                        TestDataEngineStateStandard(
//                            expression = listOf(
//                                Token.Number(lastOperand.toDouble()),
//                                Token.Binary(button.toOperator() as OperatorBinary)
//                            ),
//                            lastOperand = lastOperand,
//                            buttonBinary = button,
//                        )
//                    )
//                }
//            }
//        }

//    private val testOperands = sequenceOf(
//        "-2.5",
//        "-1",
//        "-1.0",
//        "0.0",
//        "1.0",
//        "1",
//        "2.5",
//        "NaN",
//    )
}