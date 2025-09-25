package com.example.calculatorApp.testData.scenario

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.testData.scenario.context.ContextEngineState

object ScenarioEngineStateError : ScenarioEngineState {

    override val buildInput = { context: ContextEngineState -> buildBinaryInputState<ContextEngineState.Error>(context) }
    override val buildExpected = { context: ContextEngineState -> buildBinaryExpectedState<ContextEngineState.Error>(context) }

    override fun buildContexts(
        expressionInput: List<Token>,
        lastOperand: Number,
        button: Button
    ): Pair<ContextEngineState, ContextEngineState> {
        val context = ContextEngineState.Error(
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
        return context to context
    }
}