package com.example.calculatorApp.testData.scenario.context

import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.Token.Companion.firstNumberOrNull
import com.example.calculatorApp.domain.ast.Token.Companion.lastBinaryOrNull
import com.example.calculatorApp.model.state.CalculatorStateDomain
import kotlin.math.abs

const val TOLERANCE = 1e-20

inline fun <reified T : Context> Context.requireContext(): T =
    this as? T ?: error("Requires ${T::class.simpleName} but got ${this::class.simpleName}")

infix fun CalculatorStateDomain.shouldMatch(expected: ContextEngineState.Base) {
    check(expression == expected.expression) { "expression mismatch" }
    check(lastOperand == expected.lastOperand) { "lastOperand mismatch" }
    check(lastOperator == expected.lastOperator) { "lastOperator mismatch" }
    check(activeButton == expected.activeButton) { "activeButton mismatch" }
    check(lastResult == expected.lastResult) { "lastResult mismatch" }
    check(cachedOperand == expected.cachedOperand) { "cachedOperand mismatch" }
    check(isComputed == expected.isComputed) { "isComputed mismatch" }
    check(hasError == expected.hasError) { "hasError mismatch" }
    check(errorMessage == expected.errorMessage) { "errorMessage mismatch" }
}

infix fun Double?.isCloseTo(other: Double?): Boolean =
    if (this != null && other != null) abs(this - other) <= TOLERANCE else this == other

infix fun CalculatorStateDomain.shouldMatchWithTolerance(expected: ContextEngineState.Base) {
    val (tokenValue, expectedValue) = this.getNumberValueToken(expected)
    val (tokenOperator, expectedOperator) = this.getBinaryOperatorToken(expected)

    check(tokenValue isCloseTo expectedValue) { "expression value mismatch: $tokenValue is not within $TOLERANCE of $expectedValue" }
    check(tokenOperator == expectedOperator) { "expression operator mismatch" }
    check(lastOperand == expected.lastOperand) { "lastOperand mismatch" }
    check(lastOperator == expected.lastOperator) { "lastOperator mismatch" }
    check(activeButton == expected.activeButton) { "activeButton mismatch" }
    check(lastResult == expected.lastResult) { "lastResult mismatch" }
    check(cachedOperand == expected.cachedOperand) { "cachedOperand mismatch" }
    check(isComputed == expected.isComputed) { "isComputed mismatch" }
    check(hasError == expected.hasError) { "hasError mismatch" }
    check(errorMessage == expected.errorMessage) { "errorMessage mismatch" }
}

private fun CalculatorStateDomain.getNumberValueToken(expected: ContextEngineState.Base): Pair<Double?, Double?> =
    expression.firstNumberOrNull()?.value to expected.expression.firstNumberOrNull()?.value

private fun CalculatorStateDomain.getBinaryOperatorToken(expected: ContextEngineState.Base): Pair<OperatorBinary?, OperatorBinary?> =
    expression.lastBinaryOrNull()?.operator to expected.expression.lastBinaryOrNull()?.operator