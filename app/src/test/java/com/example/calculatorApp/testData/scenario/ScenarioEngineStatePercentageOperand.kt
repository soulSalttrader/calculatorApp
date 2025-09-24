package com.example.calculatorApp.testData.scenario

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.symbols.SymbolButtonUtils.toButton
import com.example.calculatorApp.testData.scenario.context.ContextEngineState

object ScenarioEngineStatePercentageOperand : ScenarioEngineState {

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
            expression = emptyList(),
            lastOperand = lastOperand.toString(),
            lastOperator = null,

            activeButton = lastOperand.lastDigit().toString().toButton(),

            lastResult = null,
            cachedOperand = null,
            isComputed = false,

            hasError = false,
            errorMessage = null,
        )

        val expected = input.copy(
            expression = emptyList(),
            lastOperand = (lastOperand.toDouble() / 100).toString(),

            activeButton = ButtonCalculatorUnary.Percentage,
        )
        return input to expected
    }
}