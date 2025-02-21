package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.state.CalculatorState
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.utils.Constants.MAX_NUM_LENGTH

typealias Validator<T> = (T) -> Boolean

class EngineStateStandard(private val engineMath: EngineMath) : EngineState {

    override fun handleArithmetic(state: CalculatorState, arithmetic: ButtonCalculatorArithmetic): CalculatorState {
        return state.modifyWith(
            { hasNaN(state) } to { this },
            { state.activeButton == ButtonCalculatorArithmetic.Equals } to { state.copy(operator = arithmetic, operandRight = "", operand = null) },
            { hasOperatorAndOperandRight(state) } to {
                val newState = applyArithmetic(state)
                enterArithmetic(newState, arithmetic)
            },
            { true } to { enterArithmetic(state, arithmetic) }
        )
    }

    override fun applyArithmetic(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { hasInvalidInput(state) } to { this },
            { true } to {
                val operandLeft = state.operandLeft.toDouble()
                val operandRight = state.operandRight.toDouble()

                if (operandLeft.isNaN() || operandRight.isNaN()) return@to state

                val operation = state.operator as ButtonCalculatorArithmetic
                val result = engineMath.applyArithmetic(operandLeft, operation, operandRight)

                state.copy(
                    operandRight = result.toString(),
                    operandLeft = "",
                    operator = null
                )
            }
        )
    }

    override fun enterArithmetic(state: CalculatorState, arithmetic: ButtonCalculatorArithmetic): CalculatorState {
        return state.modifyWith(
            { state.operator != null } to { state.copy(operator = arithmetic) },
            { state.operandRight.isNotBlank() } to { state.copy(operandLeft = state.operandRight, operandRight = "", operator = arithmetic) },
        )
    }

    override fun applyEquals(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.operandLeft.toDoubleOrNull() == null } to { this },
            { state.operandRight.toDoubleOrNull() == null && state.operand == null } to { this },
            { state.operator !is ButtonCalculatorArithmetic } to { this },
            { true } to {
                val operandLeft = state.operandLeft.toDouble()
                val operandRight = state.operand?.toDoubleOrNull() ?: state.operandRight.toDouble()

                if (operandLeft.isNaN() || operandRight.isNaN()) return@to state

                val operation = state.operator as ButtonCalculatorArithmetic
                val result = engineMath.applyArithmetic(operandLeft, operation, operandRight)

                state.copy(
                    operandRight = result.toString(),
                    operandLeft = result.toString(),
                    operand = operandRight.toString() // Save the last entered number
                )
            }
        )
    }

    override fun enterNumber(state: CalculatorState, number: Int): CalculatorState {
        return state.modifyWith(
            { hasNaN(state) } to { this },
            { state.operandRight == SymbolButton.ZERO.label } to { copy(operandRight = number.toString()) },
            { state.operandRight.length >= MAX_NUM_LENGTH } to { this },
            { true } to { copy(operandRight = operandRight + number) }
        )
    }

    override fun enterDecimal(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { hasNaN(state) } to { this },
            { !state.operandRight.contains(".") && state.operandRight.isNotBlank() } to { state.copy(operandRight = state.operandRight + ".") },
            { !state.operandRight.contains(".") && state.operandRight.isBlank() } to { state.copy(operandRight = SymbolButton.ZERO.label + ".") }
        )
    }

    override fun applyClearAll(state: CalculatorState): CalculatorState = CalculatorState()

    override fun applyClear(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { hasNaN(state) } to { applyClearAll(state) },
            { !state.operand.isNullOrBlank() } to { applyClearAll(state) },
            { state.operator != null } to { copy(operandRight = state.operandLeft, operandLeft = "", operator = null) },
            { state.operandRight.isNotBlank() } to { copy(operandRight = SymbolButton.ZERO.label, operandLeft = "") },
        )
    }

    override fun applySign(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { hasNaN(state) } to { this },
            { isDefaultOrEmpty(state) } to { state.copy(operandRight = "-" + SymbolButton.ZERO.label) },
            { state.operandRight.toIntOrNull() != null } to {
                val intNumber = state.operandRight.toInt()
                val result = engineMath.applySign(intNumber).toString()

                state.copy(operandRight = result)
            },
            { true } to {
                val doubleNumber = state.operandRight.toDouble()
                val result = engineMath.applySign(doubleNumber).toString()

                state.copy(operandRight = result)
            }
        )
    }

    override fun applyPercent(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { hasNaN(state) } to { this },
            { state.operandRight.toDoubleOrNull() == null } to { this },
            { true } to {
                val result = engineMath.applyPercent(
                    state.operandLeft.toDoubleOrNull(),
                    state.operator as? ButtonCalculatorArithmetic,
                    state.operandRight.toDouble()
                )

                state.copy(operandRight = result.toString())
            }
        )
    }

    val hasNaN: Validator<CalculatorState> = { it.operandRight == "NaN" || it.operandLeft == "NaN" }
    val hasOperatorAndOperandRight: Validator<CalculatorState> = { it.operator != null && it.operandRight.isNotBlank() }
    val hasInvalidInput: Validator<CalculatorState> = {
        it.operandLeft.toDoubleOrNull() == null ||
        it.operandRight.toDoubleOrNull() == null && it.operand == null ||
        it.operator !is ButtonCalculatorArithmetic
    }
    val isDefaultOrEmpty: Validator<CalculatorState> = { it.operandRight == SymbolButton.ZERO.label || it.operandRight.isEmpty() }
}