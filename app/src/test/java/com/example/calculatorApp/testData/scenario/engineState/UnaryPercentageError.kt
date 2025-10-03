package com.example.calculatorApp.testData.scenario.engineState

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.symbols.SymbolButtonUtils.toButton
import com.example.calculatorApp.testData.scenario.buildUnaryExpectedState
import com.example.calculatorApp.testData.scenario.buildUnaryInputState
import com.example.calculatorApp.testData.scenario.context.ContextEngineState
import com.example.calculatorApp.testData.scenario.lastDigit

object UnaryPercentageError : EngineState.Unary {

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

            activeButton = lastOperand.lastDigit().toString().toButton(),

            lastResult = null,
            cachedOperand = null,
            isComputed = false,

            hasError = true,
            errorMessage = "Error",
        )

        val expected = input.copy(
            activeButton = ButtonCalculatorUnary.Percentage,
        )
        return input to expected
    }
}