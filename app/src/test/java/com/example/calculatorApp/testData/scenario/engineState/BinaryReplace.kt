package com.example.calculatorApp.testData.scenario.engineState

import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.testData.scenario.buildBinaryExpectedState
import com.example.calculatorApp.testData.scenario.buildBinaryInputState
import com.example.calculatorApp.testData.scenario.context.ContextEngineState

object BinaryReplace : EngineState.Binary {

    override val buildInput = { context: ContextEngineState ->
        buildBinaryInputState<ContextEngineState.Replace>(
            context
        )
    }
    override val buildExpected = { context: ContextEngineState ->
        buildBinaryExpectedState<ContextEngineState.Replace>(
            context
        )
    }

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