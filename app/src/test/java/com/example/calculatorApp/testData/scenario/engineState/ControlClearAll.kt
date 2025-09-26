package com.example.calculatorApp.testData.scenario.engineState

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.model.symbols.SymbolButtonUtils.toButton
import com.example.calculatorApp.testData.scenario.buildControlExpectedState
import com.example.calculatorApp.testData.scenario.buildControlInputState
import com.example.calculatorApp.testData.scenario.context.ContextEngineState
import com.example.calculatorApp.testData.scenario.lastDigit

object ControlClearAll : EngineState.Control {

    override val buildInput =
        { context: ContextEngineState -> buildControlInputState<ContextEngineState.Success>(context) }
    override val buildExpected =
        { context: ContextEngineState -> buildControlExpectedState<ContextEngineState.Success>(context) }

    override fun buildContexts(
        expressionInput: List<Token>,
        lastOperand: Number,
        button: Button,
    ): Pair<ContextEngineState, ContextEngineState> {

        val input = ContextEngineState.Success(
            expression = expressionInput,
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
            lastOperand = SymbolButton.ZERO.label,
            lastOperator = null,

            activeButton = null,

            lastResult = null,
            cachedOperand = null,
            isComputed = false,

            hasError = false,
            errorMessage = null,
        )
        return input to expected
    }
}