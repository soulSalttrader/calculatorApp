package com.example.calculatorApp.testData.scenario

import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.testData.expected.ExpectedEngineState
import com.example.calculatorApp.testData.expected.ExpectedEngineStateDelegate
import com.example.calculatorApp.testData.input.InputEngineState
import com.example.calculatorApp.testData.input.InputEngineStateDelegate
import com.example.calculatorApp.testData.scenario.context.ContextEngineState
import com.example.calculatorApp.testData.scenario.context.requireContext

object ScenarioEngineStateUpdate : ScenarioEngineState {

    override val buildInput = ::engineStateBinaryInputUpdate
    override val buildExpected = ::engineStateBinaryExpectedUpdate

    override fun buildContexts(
        expressionInput: List<Token>,
        lastOperand: String,
        button: Button
    ): Pair<ContextEngineState, ContextEngineState> {

        val input = ContextEngineState.Update(
            expression = expressionInput,
            lastOperand = lastOperand,
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

    fun engineStateBinaryInputUpdate(context: ContextEngineState): InputEngineState.Binary =
        InputEngineState.Binary(
            object : InputEngineStateDelegate.Binary {
                override val context: ContextEngineState.Base = context.requireContext<ContextEngineState.Update>()
            }
        )

    fun engineStateBinaryExpectedUpdate(context: ContextEngineState): ExpectedEngineState.Binary =
        ExpectedEngineState.Binary(
            object : ExpectedEngineStateDelegate.Binary {
                override val context: ContextEngineState.Base = context.requireContext<ContextEngineState.Update>()
            }
        )
}