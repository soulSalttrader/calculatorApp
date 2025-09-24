package com.example.calculatorApp.testData.scenario

import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.testData.scenario.context.ContextEngineState

object ScenarioEngineStateReplace : ScenarioEngineState {

    override val buildInput = { context: ContextEngineState -> buildInputState<ContextEngineState.Replace>(context) }
    override val buildExpected = { context: ContextEngineState -> buildExpectedState<ContextEngineState.Replace>(context) }

    override fun buildContexts(
        expressionInput: List<Token>,
        lastOperand: Number,
        button: Button,
    ): Pair<ContextEngineState, ContextEngineState> {

        val input = ContextEngineState.Replace(
            expression = expressionInput,
            lastOperand = "",
            lastOperator = button.toOperator(),

            activeButton = button,

            lastResult = null,
            cachedOperand = null,
            isComputed = false,

            hasError = false,
            errorMessage = null,
        )

        val expected = input.copy(
            expression = expressionInput.toMutableList().dropLast(1) + Token.Binary(OperatorBinary.Multiplication),
            lastOperand = "",
            lastOperator = OperatorBinary.Multiplication,
        )
        return input to expected
    }
}