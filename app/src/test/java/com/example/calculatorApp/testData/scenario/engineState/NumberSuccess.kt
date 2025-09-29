package com.example.calculatorApp.testData.scenario.engineState

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.testData.scenario.buildNumberExpectedState
import com.example.calculatorApp.testData.scenario.buildNumberInputState
import com.example.calculatorApp.testData.scenario.context.ContextEngineState

object NumberSuccess : EngineState.Numeric {

    override val buildInput = { context: ContextEngineState -> buildNumberInputState<ContextEngineState.Success>(context) }
    override val buildExpected = { context: ContextEngineState -> buildNumberExpectedState<ContextEngineState.Success>(context) }

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
            lastOperand = if (lastOperand is Int && lastOperand == 0) ButtonCalculatorNumber.Nine.symbol.label else lastOperand.toString() + ButtonCalculatorNumber.Nine.symbol.label,
            activeButton = ButtonCalculatorNumber.Nine
        )
        return input to expected
    }
}