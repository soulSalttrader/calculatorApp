package com.example.calculatorApp.testData.scenario

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.testData.expected.ExpectedEngineState
import com.example.calculatorApp.testData.expected.ExpectedEngineStateDelegate
import com.example.calculatorApp.testData.input.InputEngineState
import com.example.calculatorApp.testData.input.InputEngineStateDelegate
import com.example.calculatorApp.testData.scenario.context.ContextEngineState
import com.example.calculatorApp.testData.scenario.context.requireContext

object ScenarioEngineStateError : ScenarioEngineState {

    override val buildInput = ::engineStateBinaryInputError
    override val buildExpected = ::engineStateBinaryExpectedError

    override fun buildContexts(
        expressionInput: List<Token>,
        lastOperand: String,
        button: Button
    ): Pair<ContextEngineState, ContextEngineState> {
        val context = ContextEngineState.Error(
            expression = expressionInput,
            lastOperand = lastOperand,
            lastOperator = button.toOperator(),
            activeButton = button,
            hasError = true,
            errorMessage = "Error"
        )
        return context to context
    }

    fun engineStateBinaryInputError(context: ContextEngineState): InputEngineState.Binary =
        InputEngineState.Binary(
            object : InputEngineStateDelegate.Binary {
                override val context: ContextEngineState.Base = context.requireContext<ContextEngineState.Error>()
            }
        )

    fun engineStateBinaryExpectedError(context: ContextEngineState): ExpectedEngineState.Binary =
        ExpectedEngineState.Binary(
            object : ExpectedEngineStateDelegate.Binary {
                override val context: ContextEngineState.Base = context.requireContext<ContextEngineState.Error>()
            }
        )
}