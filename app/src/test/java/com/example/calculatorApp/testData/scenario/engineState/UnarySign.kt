package com.example.calculatorApp.testData.scenario.engineState

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.testData.TestDataEngineMathStandardSign.unaryMinus
import com.example.calculatorApp.testData.scenario.buildUnaryExpectedState
import com.example.calculatorApp.testData.scenario.buildUnaryInputState
import com.example.calculatorApp.testData.scenario.context.ContextEngineState

object UnarySign : EngineState.Unary {

    override val buildInput =
        { context: ContextEngineState -> buildUnaryInputState<ContextEngineState.Success>(context) }
    override val buildExpected =
        { context: ContextEngineState -> buildUnaryExpectedState<ContextEngineState.Success>(context) }

    override fun buildContexts(
        expressionInput: List<Token>,
        lastOperand: Number,
        button: Button,
    ): Pair<ContextEngineState, ContextEngineState> {

        val input = ContextEngineState.Success(
            expression = expressionInput,
            lastOperand = lastOperand.toString(),
            lastOperator = button.toOperator(),

            activeButton = button,

            lastResult = null,
            cachedOperand = null,
            isComputed = false,

            hasError = false,
            errorMessage = null,
        )

        val expected = input.copy(
            expression = expressionInput,
            lastOperand = if (lastOperand is Int && lastOperand == 0) "-0" else lastOperand.unaryMinus().toString(),
            lastOperator = button.toOperator(),

            activeButton = ButtonCalculatorUnary.Sign,
        )
        return input to expected
    }
}