package com.example.calculatorApp.testData.scenario

import com.example.calculatorApp.testData.expected.ExpectedEngineState
import com.example.calculatorApp.testData.expected.ExpectedEngineStateDelegate
import com.example.calculatorApp.testData.input.InputEngineState
import com.example.calculatorApp.testData.input.InputEngineStateDelegate
import com.example.calculatorApp.testData.scenario.context.ContextEngineState
import com.example.calculatorApp.testData.scenario.context.requireContext

inline fun <reified T : Scenario> Scenario.requireScenario(): T =
    this as? T ?: error("Requires ${T::class.simpleName} but got ${this::class.simpleName}")

inline fun <reified T : ContextEngineState> buildInputState(
    context: ContextEngineState
): InputEngineState.Binary =
    InputEngineState.Binary(
        object : InputEngineStateDelegate.Binary {
            override val context: ContextEngineState.Base = context.requireContext<T>() as ContextEngineState.Base
        }
    )

inline fun <reified T : ContextEngineState> buildExpectedState(
    context: ContextEngineState
): ExpectedEngineState.Binary =
    ExpectedEngineState.Binary(
        object : ExpectedEngineStateDelegate.Binary {
            override val context: ContextEngineState.Base = context.requireContext<T>() as ContextEngineState.Base
        }
    )