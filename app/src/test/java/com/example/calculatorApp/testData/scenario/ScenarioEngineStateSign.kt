package com.example.calculatorApp.testData.scenario

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.testData.TestDataEngineMathStandardSign.unaryMinus
import com.example.calculatorApp.testData.scenario.context.ContextEngineState

object ScenarioEngineStateSign : ScenarioEngineState {

    override val buildInput =
        { context: ContextEngineState -> buildInputState<ContextEngineState.Success>(context) }
    override val buildExpected =
        { context: ContextEngineState -> buildExpectedState<ContextEngineState.Success>(context) }

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
            lastOperand = lastOperand.unaryMinus().toString(),
            lastOperator = button.toOperator(),

            activeButton = ButtonCalculatorUnary.Sign,
        )
        return input to expected
    }
}