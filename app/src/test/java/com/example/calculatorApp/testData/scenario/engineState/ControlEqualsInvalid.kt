package com.example.calculatorApp.testData.scenario.engineState

import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.symbols.SymbolButtonUtils.toButton
import com.example.calculatorApp.testData.scenario.buildControlExpectedState
import com.example.calculatorApp.testData.scenario.buildControlInputState
import com.example.calculatorApp.testData.scenario.context.ContextEngineState
import com.example.calculatorApp.testData.scenario.lastDigit

object ControlEqualsInvalid : EngineState.Control {

    override val buildInput =
        { context: ContextEngineState -> buildControlInputState<ContextEngineState.Error>(context) }
    override val buildExpected =
        { context: ContextEngineState -> buildControlExpectedState<ContextEngineState.Error>(context) }

    override fun buildContexts(
        expressionInput: List<Token>,
        lastOperand: Number,
        button: Button,
    ): Pair<ContextEngineState, ContextEngineState> {

        val input = ContextEngineState.Error(
            expression = listOf(
                Token.Number(Double.POSITIVE_INFINITY),
                Token.Binary(button.toOperator() as OperatorBinary)
            ),
            lastOperand = lastOperand.toString(),
            lastOperator = button.toOperator(),

            activeButton = lastOperand.lastDigit().toString().toButton(),

            lastResult = null,
            cachedOperand = null,
            isComputed = false,

            hasError = false,
            errorMessage = null,
        )

        val expected = input.copy(
            expression = emptyList(),
            lastOperand = "",

            activeButton = ButtonCalculatorControl.Equals,

            isComputed = false,

            hasError = true,
            errorMessage = "Invalid expression"
        )
        return input to expected
    }
}