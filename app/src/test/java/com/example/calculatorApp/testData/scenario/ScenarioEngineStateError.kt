package com.example.calculatorApp.testData.scenario

import com.example.calculatorApp.model.state.HasState
import com.example.calculatorApp.testData.expected.ExpectedEngineState
import com.example.calculatorApp.testData.expected.ExpectedEngineStateDelegate
import com.example.calculatorApp.testData.input.InputEngineState
import com.example.calculatorApp.testData.input.InputEngineStateDelegate
import com.example.calculatorApp.testData.scenario.context.ContextEngineState
import com.example.calculatorApp.testData.scenario.context.requireContext

object ScenarioEngineStateError : ScenarioEngineState {

    override val factoryContext = ::engineStateContextError
    override val factoryInput = ::engineStateBinaryInputError
    override val factoryExpected = ::engineStateBinaryExpectedError

    fun engineStateContextError(hasState: HasState): ContextEngineState = ContextEngineState.Error(
        hasState.expression, hasState.lastOperand,
        hasState.lastOperator, hasState.activeButton,
        hasState.hasError, hasState.errorMessage
    )

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