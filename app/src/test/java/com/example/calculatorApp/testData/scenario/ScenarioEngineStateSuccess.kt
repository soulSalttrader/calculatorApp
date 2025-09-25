package com.example.calculatorApp.testData.scenario

import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.testData.scenario.context.ContextEngineState

object ScenarioEngineStateSuccess : ScenarioEngineState {

    override val buildInput = { context: ContextEngineState -> buildBinaryInputState<ContextEngineState.Success>(context)}
    override val buildExpected = { context: ContextEngineState -> buildBinaryExpectedState<ContextEngineState.Success>(context)}

    override fun buildContexts(
        expressionInput: List<Token>,
        lastOperand: Number,
        button: Button
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