package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.state.CalculatorState
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.utils.Constants.MAX_NUM_LENGTH

class EngineStateStandard(private val engineMath: EngineMath) : EngineState {

    override fun handleArithmetic(state: CalculatorState, arithmetic: ButtonCalculatorArithmetic): CalculatorState {
        return state.modifyWith(
            { state.operandRight.toDoubleOrNull()?.isNaN() == true } to { this },
            { state.activeButton == ButtonCalculatorArithmetic.Equals } to { state.copy(operator = arithmetic, operandRight = "", operand = null) },
            { state.operator != null && state.operandRight.isNotBlank() } to {
                val newState = applyArithmetic(state)
                enterArithmetic(newState, arithmetic)
            },
            { true } to { enterArithmetic(state, arithmetic) }
        )
    }

    override fun applyArithmetic(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.operandLeft.toDoubleOrNull() == null } to { this },
            { state.operandRight.toDoubleOrNull() == null } to { this },
            { state.operator !is ButtonCalculatorArithmetic } to { this },
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
            { state.operandLeft.toDoubleOrNull()?.isNaN() == true } to { this },
            { state.operandRight.toDoubleOrNull()?.isNaN() == true } to { this },
            { state.operandRight == "0" } to { copy(operandRight = number.toString()) },
            { state.operandRight.length >= MAX_NUM_LENGTH } to { this },
            { true } to { copy(operandRight = operandRight + number) }
        )
    }

    override fun enterDecimal(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.operandLeft.toDoubleOrNull()?.isNaN() == true } to { this },
            { state.operandRight.toDoubleOrNull()?.isNaN() == true } to { this },
            { !state.operandRight.contains(".") && state.operandRight.isNotBlank() } to { state.copy(operandRight = state.operandRight + ".") },
            { !state.operandRight.contains(".") && state.operandRight.isBlank() } to { state.copy(operandRight = "0" + ".") }
        )
    }

    override fun applyClearAll(state: CalculatorState): CalculatorState = CalculatorState()

    override fun applyClear(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.operandLeft.toDoubleOrNull()?.isNaN() == true } to { applyClearAll(state) },
            { state.operandRight.toDoubleOrNull()?.isNaN() == true } to { applyClearAll(state) },
            { !state.operand.isNullOrBlank() } to { applyClearAll(state) },
            { state.operator != null } to { copy(operandRight = state.operandLeft, operandLeft = "", operator = null) },
            { state.operandRight.isNotBlank() } to { copy(operandRight = SymbolButton.ZERO.label, operandLeft = "") },
        )
    }

    override fun applySign(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.operandRight.toDoubleOrNull()?.isNaN() == true } to { this },
            { state.operandRight.toDoubleOrNull() == null } to { this },
            { true } to {
                val result = engineMath.applySign(state.operandRight.toDouble())
                state.copy(operandRight = result.toString())
            }
        )
    }

    override fun applyPercent(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.operandRight.toDoubleOrNull()?.isNaN() == true } to { this },
            { state.operandRight.toDoubleOrNull() == null } to { this },
            { true } to {
                val result = engineMath.applyPercent(state.operandLeft.toDoubleOrNull(), state.operandRight.toDouble())
                state.copy(operandRight = result.toString())
            }
        )
    }
}