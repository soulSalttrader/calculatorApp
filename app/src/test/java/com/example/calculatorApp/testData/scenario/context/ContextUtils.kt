package com.example.calculatorApp.testData.scenario.context

import com.example.calculatorApp.model.state.CalculatorStateDomain

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