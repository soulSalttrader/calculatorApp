package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.state.CalculatorState
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.utils.Constants.MAX_NUM_LENGTH

class EngineStateStandard(private val engineMath: EngineMath) : EngineState {

    override fun enterArithmetic(state: CalculatorState, arithmetic: ButtonCalculatorArithmetic): CalculatorState {
        return state.modifyWith(
            { state.operation != null } to { state.copy(operation = arithmetic) },
            { state.currentNumber.isNotBlank() } to { state.copy(previousNumber = state.currentNumber, currentNumber = "", operation = arithmetic) },
        )
    }

    override fun enterNumber(state: CalculatorState, number: Int): CalculatorState {
        return state.modifyWith(
            { state.currentNumber == "0" } to { copy(currentNumber = number.toString()) },
            { state.currentNumber.length >= MAX_NUM_LENGTH } to { this },
            { true } to { copy(currentNumber = currentNumber + number) }
        )
    }

    override fun enterDecimal(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { !state.currentNumber.contains(".") } to { state.copy(currentNumber = state.currentNumber + ".") }
        )
    }

    override fun applyClearAll(state: CalculatorState): CalculatorState = CalculatorState()

    override fun applyClear(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.operation != null } to { copy(currentNumber = state.previousNumber, previousNumber = "", operation = null) },
            { state.currentNumber.isNotBlank() } to { copy(currentNumber = SymbolButton.ZERO.label, previousNumber = "") },
        )
    }

    override fun applyArithmetic(state: CalculatorState): CalculatorState {
        val left = state.previousNumber.toDoubleOrNull() ?: return state
        val right = state.currentNumber.toDoubleOrNull() ?: return state
        val operation = state.operation as? ButtonCalculatorArithmetic ?: return state

        val result = engineMath.applyArithmetic(left, right, operation)

        return state.copy(
            currentNumber = result.toString(),
            previousNumber = "",
            operation = null,
        )
    }

    override fun applySign(state: CalculatorState): CalculatorState {
        val number = state.currentNumber.toDoubleOrNull() ?: return state
        val result = engineMath.applySign(number)
        return state.copy(currentNumber = result.toString())
    }

    override fun applyPercent(state: CalculatorState): CalculatorState {
        val number = state.currentNumber.toDoubleOrNull() ?: return state
        val result = engineMath.applyPercent(number)
        return state.copy(currentNumber = result.toString())
    }
}