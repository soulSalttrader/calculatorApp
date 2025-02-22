package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.state.CalculatorState
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.utils.Constants.MAX_NUM_LENGTH

class EngineStateStandard(private val engineMath: EngineMath) : EngineState {

        override fun handleArithmetic(state: CalculatorState, arithmetic: ButtonCalculatorBinary): CalculatorState {
        return state.modifyWith(
            { state.lastInput == "NaN" || state.expression.contains("NaN") } to { this },
            { state.activeButton == ButtonCalculatorBinary.Equals } to { state.copy(lastOperator = arithmetic, lastInput = "", lastResult = null) },
            { state.lastOperator != null && state.lastInput.isNotBlank() } to {
                val newState = applyArithmetic(state)
                enterArithmetic(newState, arithmetic)
            },
            { true } to { enterArithmetic(state, arithmetic) }
        )
    }

    override fun enterArithmetic(state: CalculatorState, arithmetic: ButtonCalculatorBinary): CalculatorState {
        return state.modifyWith(
            { state.lastOperator != null } to { state.copy(lastOperator = arithmetic) },
            { state.lastInput.isNotBlank() } to { state.copy(expression = listOf(state.lastInput, arithmetic.symbol.label), lastInput = "", lastOperator = arithmetic) },
        )
    }

    override fun applyArithmetic(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.expression.dropLast(1).lastOrNull()?.toDoubleOrNull() == null } to { this },
            { state.lastInput.toDoubleOrNull() == null } to { this },
            { state.lastOperator !is ButtonCalculatorBinary } to { this },
            { true } to {
                val operandLeft = state.expression.dropLast(1).lastOrNull()?.toDoubleOrNull() ?: 0.0
                val operandRight = state.lastInput.toDouble()

                val operation = state.lastOperator as ButtonCalculatorBinary
                val result = engineMath.applyArithmetic(operandLeft, operation, operandRight)

                state.copy(
                    lastInput = result.toString(),
                    lastResult = result.toString(),
                    expression = state.expression.dropLast(1),
                    lastOperator = null
                )
            }
        )
    }

    override fun applyEquals(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.expression.dropLast(1).lastOrNull()?.toDoubleOrNull() == null } to { this },
            { state.lastInput.toDoubleOrNull() == null && state.subResult == null } to { this },
            { state.lastOperator !is ButtonCalculatorBinary } to { this },
            { true } to {
                val operandLeft = state.expression.dropLast(1).lastOrNull()?.toDoubleOrNull() ?: 0.0
                val operandRight = state.subResult?.toDoubleOrNull() ?: state.lastInput.toDouble()

                if (operandLeft.isNaN() || operandRight.isNaN()) return@to state

                val operation = state.lastOperator as ButtonCalculatorBinary
                val result = engineMath.applyArithmetic(operandLeft, operation, operandRight)

                state.copy(
                    lastInput = result.toString(),
                    expression = listOf(result.toString(), operation.symbol.label),
                    subResult = operandRight.toString(), // Save the last entered number
                    activeButton = ButtonCalculatorBinary.Equals
                )
            }
        )
    }

    override fun enterNumber(state: CalculatorState, number: Int): CalculatorState {
        return state.modifyWith(
            { state.lastInput.toDoubleOrNull()?.isNaN() == true } to { this },
            { state.expression.isEmpty() && state.lastInput == "0" } to {
                state.copy(lastInput = number.toString())
            },
            { state.lastInput.length >= MAX_NUM_LENGTH } to { this },
            { true } to {
                state.copy(
                    lastInput = state.lastInput + number.toString(),
                )
            }
        )
    }

    override fun enterDecimal(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.expression.lastOrNull()?.toDoubleOrNull()?.isNaN() == true } to { this },
            { state.lastInput.toDoubleOrNull()?.isNaN() == true } to { this },
            { !state.lastInput.contains(".") && state.lastInput.isNotBlank() } to { state.copy(lastInput = state.lastInput  + ".") },
            { !state.lastInput.contains(".") && state.lastInput.isBlank() } to { state.copy(lastInput = "0" + ".") }
        )
    }

    override fun applyClearAll(state: CalculatorState): CalculatorState = CalculatorState()

    override fun applyClear(state: CalculatorState): CalculatorState {
        return state.modifyWith(
//            { true } to { clearAll() },
            { state.activeButton == ButtonCalculatorBinary.Equals } to { applyClearAll(state) },
            { state.expression.lastOrNull()?.toDoubleOrNull()?.isNaN() == true } to { applyClearAll(state) },
            { state.lastInput.dropLast(1).toDoubleOrNull()?.isNaN() == true } to { applyClearAll(state) },
            { state.expression.isNotEmpty() } to { state.copy(expression = expression.dropLast(1)) },
            { true } to { state.copy(lastInput = "0", lastResult = null, lastOperator = null, subResult = null, pendingOperator = null, isComputed = false) }
        )
    }

    override fun applySign(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.expression.contains("NaN") } to { this },
            { state.lastInput == SymbolButton.ZERO.label || state.lastInput.isEmpty() } to { state.copy(lastInput = "-" + SymbolButton.ZERO.label) },
            { state.lastInput.toIntOrNull() != null } to {
                val intNumber = state.lastInput.toInt()
                val result = engineMath.applySign(intNumber).toString()

                state.copy(lastInput = result)
            },
            { true } to {
                val doubleNumber = state.lastInput.toDouble()
                val result = engineMath.applySign(doubleNumber).toString()

                state.copy(lastInput = result)
            }
        )
    }

    override fun applyPercent(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.lastInput.toDoubleOrNull() == null } to { this },
            { state.expression.isNotEmpty() } to {
                val operandLeft = state.expression.dropLast(1).lastOrNull()?.toDoubleOrNull()
                val operandRight = state.lastInput.toDoubleOrNull()!!
                val operator = state.lastOperator as ButtonCalculatorBinary
                val result = engineMath.applyPercent(operandLeft, operator, operandRight)

                state.copy(lastInput = result.toString())
            },
            { true } to {
                val result = engineMath.applyPercent(null, null, state.lastInput.toDouble())
                state.copy(lastInput = result.toString())
            }
        )
    }
}