package com.example.calculatorApp.testData.scenario

import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.testData.expected.ExpectedEngineState
import com.example.calculatorApp.testData.expected.ExpectedEngineStateDelegate
import com.example.calculatorApp.testData.input.InputEngineState
import com.example.calculatorApp.testData.input.InputEngineStateDelegate
import com.example.calculatorApp.testData.scenario.context.ContextEngineState
import com.example.calculatorApp.testData.scenario.context.requireContext
import kotlin.math.pow

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

inline fun <reified T : ContextEngineState> buildControlInputState(
    context: ContextEngineState
): InputEngineState =
    InputEngineState.Control(
        object : InputEngineStateDelegate.Base {
            override val context: ContextEngineState.Base = context.requireContext<T>() as ContextEngineState.Base
        }
    )

inline fun <reified T : ContextEngineState> buildNumberInputState(
    context: ContextEngineState
): InputEngineState =
    InputEngineState.Number(
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

inline fun <reified T : ContextEngineState> buildControlExpectedState(
    context: ContextEngineState
): ExpectedEngineState =
    ExpectedEngineState.Control(
        object : ExpectedEngineStateDelegate.Base {
            override val context: ContextEngineState.Base = context.requireContext<T>() as ContextEngineState.Base
        }
    )

inline fun <reified T : ContextEngineState> buildNumberExpectedState(
    context: ContextEngineState
): ExpectedEngineState =
    ExpectedEngineState.Number(
        object : ExpectedEngineStateDelegate.Base {
            override val context: ContextEngineState.Base = context.requireContext<T>() as ContextEngineState.Base
        }
    )

fun Number.lastDigit(): Int = this.toString().last { it.isDigit() }.digitToInt()

fun Number.repeatedOperand(maxLength: Int): String =
    this.toString() + this.lastDigit().toString().repeat(maxLength - 1)

fun Operator.toFunction(): (Double, Double) -> Double = when (this) {
    OperatorBinary.Addition       -> Double::plus
    OperatorBinary.Division       -> Double::div
    OperatorBinary.Multiplication -> Double::times
    OperatorBinary.Subtraction    -> Double::minus
    else -> throw IllegalArgumentException("Unknown Operator.")
}

fun Double.expectedRepeatEqualsResult(
    repeatedOperand: Double,
    repeatCount: Int,
    operatorButton: ButtonCalculatorBinary,
): Double {

    return when (operatorButton) {
        is ButtonCalculatorBinary.Addition       -> this + repeatedOperand * repeatCount
        is ButtonCalculatorBinary.Subtraction    -> this - repeatedOperand * repeatCount
        is ButtonCalculatorBinary.Multiplication -> this * repeatedOperand.pow(repeatCount)
        is ButtonCalculatorBinary.Division       -> this / repeatedOperand.pow(repeatCount)
    }
}