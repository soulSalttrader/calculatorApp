package com.example.calculatorApp.testData.scenario.engineState

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.testData.scenario.buildNumberExpectedState
import com.example.calculatorApp.testData.scenario.buildNumberInputState
import com.example.calculatorApp.testData.scenario.context.ContextEngineState

object NumberError : EngineState.Numeric {

    override val buildInput = { context: ContextEngineState -> buildNumberInputState<ContextEngineState.Error>(context) }
    override val buildExpected = { context: ContextEngineState -> buildNumberExpectedState<ContextEngineState.Error>(context) }

    override fun buildContexts(
        expressionInput: List<Token>,
        lastOperand: Number,
        button: Button
    ): Pair<ContextEngineState, ContextEngineState> {
        val input = ContextEngineState.Error(
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

        val expected = input.copy(
            activeButton = ButtonCalculatorBinary.Multiplication
        )
        return input to expected
    }
}