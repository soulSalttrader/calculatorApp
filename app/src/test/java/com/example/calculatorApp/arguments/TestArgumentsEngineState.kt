package com.example.calculatorApp.arguments

import com.example.calculatorApp.TestData.TestDataEngineStateStandard
import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.utils.ButtonCalculatorList

object TestArgumentsEngineState {

    fun provideStateBinary(
        lastOperands: Sequence<String> = testOperands,
        buttonsBinary: Sequence<Button> = ButtonCalculatorList.binary,
    ): Sequence<TestDataEngineStateStandard> =
        sequence {
            buttonsBinary.forEach { button ->
                lastOperands.forEach { lastOperand ->
                    yield(
                        TestDataEngineStateStandard(
                            expression = listOf(
                                Token.Number(lastOperand.toDouble()),
                                Token.Binary(button.toOperator() as OperatorBinary)
                            ),
                            lastOperand = lastOperand,
                            buttonBinary = button,
                        )
                    )
                }
            }
        }

    // Skipping Subtraction and Division to avoid redundant test cases.
    // Addition and Multiplication cover the same result patterns,
    // making Subtraction and Division unnecessary for efficiency.
    fun provideStateUnary(
        lastOperands: Sequence<String> = testOperands,
        buttonsBinary: Sequence<Button> = ButtonCalculatorList.binary
            .filter { it == ButtonCalculatorBinary.Addition || it == ButtonCalculatorBinary.Multiplication },
        buttonsUnary: Sequence<Button> = ButtonCalculatorList.unary,
    ): Sequence<TestDataEngineStateStandard> =
        sequence {
            buttonsUnary.forEach { buttonUnary ->
                lastOperands.forEach { lastOperand ->
                    buttonsBinary.forEach { buttonBinary ->
                        yield(
                            TestDataEngineStateStandard(
                                lastOperand = lastOperand,
                                buttonUnary = buttonUnary,
                                buttonBinary = buttonBinary,
                            )
                        )
                    }
                }
            }
        }

    fun provideStateControl(
        lastOperands: Sequence<String> = testOperands,
        buttonsBinary: Sequence<Button> = ButtonCalculatorList.binary
            .filter { it == ButtonCalculatorBinary.Addition || it == ButtonCalculatorBinary.Division },
    ): Sequence<TestDataEngineStateStandard> =
        sequence {
            buttonsBinary.forEach { button ->
                lastOperands.forEach { lastOperand ->
                    yield(
                        TestDataEngineStateStandard(
                            expression = listOf(
                                Token.Number(lastOperand.toDouble()),
                                Token.Binary(button.toOperator() as OperatorBinary),
                            ),
                            lastOperand = lastOperand,
                            buttonBinary = button,
                            buttonUnary = ButtonCalculatorUnary.Percentage,
                        )
                    )
                }
            }
        }

    fun provideStateNumber(
        lastOperands: Sequence<String> = testOperands,
        buttonsBinary: Sequence<Button> = ButtonCalculatorList.binary,
    ): Sequence<TestDataEngineStateStandard> =
        sequence {
            buttonsBinary.forEach { button ->
                lastOperands.forEach { lastOperand ->
                    yield(
                        TestDataEngineStateStandard(
                            expression = listOf(
                                Token.Number(lastOperand.toDouble()),
                                Token.Binary(button.toOperator() as OperatorBinary)
                            ),
                            lastOperand = lastOperand,
                            buttonBinary = button,
                        )
                    )
                }
            }
        }

    private val testOperands = sequenceOf(
        "-2.5",
        "-1",
        "-1.0",
        "0.0",
        "1.0",
        "1",
        "2.5",
        "NaN",
    )
}