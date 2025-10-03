package com.example.calculatorApp.testData.scenario.engineState

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.testData.TestDataEngineMathStandardSign.unaryMinus
import com.example.calculatorApp.testData.scenario.buildUnaryExpectedState
import com.example.calculatorApp.testData.scenario.buildUnaryInputState
import com.example.calculatorApp.testData.scenario.context.ContextEngineState

object UnarySignError : EngineState.Unary {

    override val buildInput =
        { context: ContextEngineState -> buildUnaryInputState<ContextEngineState.Error>(context) }
    override val buildExpected =
        { context: ContextEngineState -> buildUnaryExpectedState<ContextEngineState.Error>(context) }

    override fun buildContexts(
        expressionInput: List<Token>,
        lastOperand: Number,
        button: Button,
    ): Pair<ContextEngineState, ContextEngineState> {

        val input = ContextEngineState.Error(
            expression = expressionInput,
            lastOperand = lastOperand.toString(),
            lastOperator = button.toOperator(),

            activeButton = button,

            lastResult = null,
            cachedOperand = null,
            isComputed = false,

            hasError = true,
            errorMessage = "Error",
        )

        val expected = input.copy(
            activeButton = ButtonCalculatorUnary.Sign,
        )
        return input to expected
    }
}