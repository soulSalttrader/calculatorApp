package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.state.CalculatorState
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.utils.Constants.MAX_NUM_LENGTH

class EngineStateStandard : EngineState {

    override fun enterArithmetic(state: CalculatorState, operation: ButtonCalculatorArithmetic): CalculatorState {
        return state.modifyWith(
            { state.operation != null } to { state.copy(operation = operation) },
            { state.currentNumber.isNotBlank() } to { state.copy(previousNumber = state.currentNumber, currentNumber = "", operation = operation) },
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
}