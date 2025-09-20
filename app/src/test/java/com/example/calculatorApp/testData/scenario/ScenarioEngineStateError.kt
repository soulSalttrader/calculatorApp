package com.example.calculatorApp.testData.scenario

import com.example.calculatorApp.model.state.HasState
import com.example.calculatorApp.testData.expected.ExpectedEngineState
import com.example.calculatorApp.testData.expected.ExpectedEngineStateDelegate
import com.example.calculatorApp.testData.input.InputEngineState
import com.example.calculatorApp.testData.input.InputEngineStateDelegate
import com.example.calculatorApp.testData.scenario.context.ContextEngineState

object ScenarioEngineStateError : ScenarioEngineState {

    override val factoryContext = ::engineStateContextError
    override val factoryInput = ::engineStateBinaryInputError
    override val factoryExpected = ::engineStateBinaryExpectedError

    fun engineStateContextError(hasState: HasState): ContextEngineState = ContextEngineState.Error(
        hasState.expression, hasState.lastOperand,
        hasState.lastOperator, hasState.activeButton,
        hasState.hasError, hasState.errorMessage
    )

    fun engineStateBinaryInputError(context: ContextEngineState): InputEngineState.Binary {
        require(context is ContextEngineState.Error) {
            "engineStateBinaryInputError requires ContextEngineState.Error but got ${context::class.simpleName}"
        }

        return InputEngineState.Binary(
            object : InputEngineStateDelegate.Binary {
                override val context: ContextEngineState.Base = context
            }
        )
    }

    fun engineStateBinaryExpectedError(context: ContextEngineState): ExpectedEngineState.Binary {
        require(context is ContextEngineState.Error) {
            "engineStateBinaryExpectedError requires ContextEngineState.Error but got ${context::class.simpleName}"
        }

        return ExpectedEngineState.Binary(
            object : ExpectedEngineStateDelegate.Binary {
                override val context: ContextEngineState.Base = context
            }
        )
    }
}