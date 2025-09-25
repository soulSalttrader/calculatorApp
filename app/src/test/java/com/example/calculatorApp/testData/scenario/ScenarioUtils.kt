package com.example.calculatorApp.testData.scenario

import com.example.calculatorApp.testData.expected.ExpectedEngineState
import com.example.calculatorApp.testData.expected.ExpectedEngineStateDelegate
import com.example.calculatorApp.testData.input.InputEngineState
import com.example.calculatorApp.testData.input.InputEngineStateDelegate
import com.example.calculatorApp.testData.scenario.context.ContextEngineState
import com.example.calculatorApp.testData.scenario.context.requireContext

inline fun <reified T : Scenario> Scenario.requireScenario(): T =
    this as? T ?: error("Requires ${T::class.simpleName} but got ${this::class.simpleName}")

inline fun <reified T : ContextEngineState> buildBinaryInputState(
    context: ContextEngineState
): InputEngineState =
    InputEngineState.Binary(
        object : InputEngineStateDelegate.Base {
            override val context: ContextEngineState.Base = context.requireContext<T>() as ContextEngineState.Base
        }
    )

inline fun <reified T : ContextEngineState> buildUnaryInputState(
    context: ContextEngineState
): InputEngineState =
    InputEngineState.Unary(
        object : InputEngineStateDelegate.Base {
            override val context: ContextEngineState.Base = context.requireContext<T>() as ContextEngineState.Base
        }
    )

inline fun <reified T : ContextEngineState> buildBinaryExpectedState(
    context: ContextEngineState
): ExpectedEngineState =
    ExpectedEngineState.Binary(
        object : ExpectedEngineStateDelegate.Base {
            override val context: ContextEngineState.Base = context.requireContext<T>() as ContextEngineState.Base
        }
    )

inline fun <reified T : ContextEngineState> buildUnaryExpectedState(
    context: ContextEngineState
): ExpectedEngineState =
    ExpectedEngineState.Unary(
        object : ExpectedEngineStateDelegate.Base {
            override val context: ContextEngineState.Base = context.requireContext<T>() as ContextEngineState.Base
        }
    )

fun Number.lastDigit(): Int {
    val value = this.toString().removePrefix("-").replace(".", "")
    return value.last().digitToInt()
}