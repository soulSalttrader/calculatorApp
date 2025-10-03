package com.example.calculatorApp.testData.scenario.engineState

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.testData.scenario.buildBinaryExpectedState
import com.example.calculatorApp.testData.scenario.buildBinaryInputState
import com.example.calculatorApp.testData.scenario.context.ContextEngineState

object BinaryError : EngineState.Binary {

    override val buildInput = { context: ContextEngineState ->
        buildBinaryInputState<ContextEngineState.Error>(
            context
        )
    }
    override val buildExpected = { context: ContextEngineState ->
        buildBinaryExpectedState<ContextEngineState.Error>(
            context
        )
    }

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