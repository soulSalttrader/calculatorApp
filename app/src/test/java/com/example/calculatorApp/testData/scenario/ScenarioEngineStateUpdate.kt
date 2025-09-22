package com.example.calculatorApp.testData.scenario

import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.testData.scenario.context.ContextEngineState

object ScenarioEngineStateUpdate : ScenarioEngineState {

    override val buildInput = { context: ContextEngineState -> buildInputState<ContextEngineState.Update>(context) }
    override val buildExpected = { context: ContextEngineState -> buildExpectedState<ContextEngineState.Update>(context) }

    override fun buildContexts(
        expressionInput: List<Token>,
        lastOperand: String,
        button: Button
    ): Pair<ContextEngineState, ContextEngineState> {

        val input = ContextEngineState.Update(
            expression = expressionInput,
            lastOperand = lastOperand,
            lastOperator = button.toOperator(),

            activeButton = button,
            lastResult = null,
            cachedOperand = null,
            isComputed = false,

            hasError = false,
            errorMessage = null,
        )

        val expected = input.copy(
            expression = expressionInput + listOf(
                Token.Number(lastOperand.toDouble()),
                Token.Binary(OperatorBinary.Multiplication)
            ),
            lastOperand = "",
            lastOperator = OperatorBinary.Multiplication,
        )
        return input to expected
    }
}