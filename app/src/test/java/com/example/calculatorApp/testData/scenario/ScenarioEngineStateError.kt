package com.example.calculatorApp.testData.scenario

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.testData.scenario.context.ContextEngineState

object ScenarioEngineStateError : ScenarioEngineState {

    override val buildInput = { context: ContextEngineState -> buildInputState<ContextEngineState.Error>(context) }
    override val buildExpected = { context: ContextEngineState -> buildExpectedState<ContextEngineState.Error>(context) }

    override fun buildContexts(
        expressionInput: List<Token>,
        lastOperand: String,
        button: Button
    ): Pair<ContextEngineState, ContextEngineState> {
        val context = ContextEngineState.Error(
            expression = expressionInput,
            lastOperand = lastOperand,
            lastOperator = button.toOperator(),
            activeButton = button,
            hasError = true,
            errorMessage = "Error"
        )
        return context to context
    }
}