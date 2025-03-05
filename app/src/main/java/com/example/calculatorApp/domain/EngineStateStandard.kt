package com.example.calculatorApp.domain

import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.state.CalculatorState
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.utils.Constants.MAX_NUM_LENGTH

class EngineStateStandard(private val engineMath: EngineMath) : EngineState {

    override fun handleBinary(state: CalculatorState, binary: ButtonCalculatorBinary): CalculatorState {
        return state.modifyWith(
            { state.lastOperand == "NaN" || state.expression.contains("NaN") } to { this },
            { state.activeButton == ButtonCalculatorControl.Equals } to { state.copy(lastOperator = binary, lastOperand = "", lastResult = null) },
            { state.lastOperator != null && state.lastOperand.isNotBlank() } to {
                val newState = applyBinary(state)
                enterBinary(newState, binary)
            },
            { true } to { enterBinary(state, binary) }
        )
    }

    override fun handleUnary(
        state: CalculatorState,
        unary: ButtonCalculatorUnary
    ): CalculatorState {
        return state.modifyWith(
            { true } to {
                when (unary) {
                    is ButtonCalculatorUnary.Sign -> applySign(state)
                    is ButtonCalculatorUnary.Percentage -> applyPercent(state)
                }
            }
        )
    }

    override fun handleControl(
        state: CalculatorState,
        control: ButtonCalculatorControl
    ): CalculatorState {
        return state.modifyWith(
            { true } to {
                when (control) {
                    is ButtonCalculatorControl.AllClear -> applyClearAll()
                    is ButtonCalculatorControl.Clear -> applyClear(state)
                    is ButtonCalculatorControl.Decimal -> enterDecimal(state)
                    is ButtonCalculatorControl.Equals -> applyEquals(state)
                }
            }
        )
    }

    override fun handleNumber(state: CalculatorState, number: ButtonCalculatorNumber): CalculatorState {
        return state.modifyWith(
            { true } to {
                applyNumber(state, number)
            }
        )
    }

    private fun applyNumber(state: CalculatorState, number: ButtonCalculatorNumber): CalculatorState {
        return state.modifyWith(
            { state.lastOperand.toDoubleOrNull()?.isNaN() == true } to { state },
            { state.expression.isEmpty() && state.lastOperand == "0" } to {
                state.copy(lastOperand = number.symbol.label)
            },
            { state.lastOperand.length >= MAX_NUM_LENGTH } to { state },
            { true } to {
                state.copy(lastOperand = state.lastOperand + number.symbol.label)
            }
        )
    }

    private fun enterBinary(state: CalculatorState, binary: ButtonCalculatorBinary): CalculatorState {
        return state.modifyWith(
            { state.lastOperator != null } to { state.copy(lastOperator = binary) },
            { state.lastOperand.isNotBlank() } to { state.copy(expression = listOf(state.lastOperand, binary.symbol.label), lastOperand = "", lastOperator = binary) },
        )
    }

    private fun applyBinary(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.expression.dropLast(1).lastOrNull()?.toDoubleOrNull() == null } to { this },
            { state.lastOperand.toDoubleOrNull() == null } to { this },
            { state.lastOperator !is ButtonCalculatorBinary } to { this },
            { true } to {
                val operandLeft = state.expression.dropLast(1).lastOrNull()?.toDoubleOrNull() ?: 0.0
                val operandRight = state.lastOperand.toDouble()

                val operation = state.lastOperator as ButtonCalculatorBinary
                val result = engineMath.applyArithmetic(operandLeft, operation, operandRight)

                state.copy(
                    lastOperand = result.toString(),
                    lastResult = result.toString(),
                    expression = state.expression.dropLast(1),
                    lastOperator = null
                )
            }
        )
    }

    private fun applySign(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.expression.contains("NaN") } to { this },
            { state.lastOperand == SymbolButton.ZERO.label || state.lastOperand.isEmpty() } to { state.copy(lastOperand = "-" + SymbolButton.ZERO.label) },
            { state.lastOperand.toIntOrNull() != null } to {
                val intNumber = state.lastOperand.toInt()
                val result = engineMath.applySign(intNumber).toString()

                state.copy(lastOperand = result)
            },
            { true } to {
                val doubleNumber = state.lastOperand.toDouble()
                val result = engineMath.applySign(doubleNumber).toString()

                state.copy(lastOperand = result)
            }
        )
    }

    private fun applyPercent(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.lastOperand.toDoubleOrNull() == null } to { this },
            { state.expression.isNotEmpty() } to {
                val operandLeft = state.expression.dropLast(1).lastOrNull()?.toDoubleOrNull()
                val operandRight = state.lastOperand.toDoubleOrNull() ?: return@to state.copy(hasError = true, errorMessage = "Invalid last operand")
                val operator = state.lastOperator as ButtonCalculatorBinary
                val result = engineMath.applyPercent(operandLeft, operator, operandRight)

                state.copy(lastOperand = result.toString())
            },
            { true } to {
                val result = engineMath.applyPercent(null, null, state.lastOperand.toDouble())
                state.copy(lastOperand = result.toString())
            }
        )
    }

    private fun enterDecimal(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.expression.lastOrNull()?.toDoubleOrNull()?.isNaN() == true } to { this },
            { state.lastOperand.toDoubleOrNull()?.isNaN() == true } to { this },
            { !state.lastOperand.contains(".") && state.lastOperand.isNotBlank() } to { state.copy(lastOperand = state.lastOperand  + ".") },
            { !state.lastOperand.contains(".") && state.lastOperand.isBlank() } to { state.copy(lastOperand = "0" + ".") }
        )
    }

    private fun applyEquals(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.expression.dropLast(1).lastOrNull()?.toDoubleOrNull() == null } to { this },
            { state.lastOperand.toDoubleOrNull() == null && state.cachedOperand == null } to { this },
            { state.lastOperator !is ButtonCalculatorBinary } to { this },
            { true } to {
                val operandLeft = state.expression.dropLast(1).lastOrNull()?.toDoubleOrNull() ?: 0.0
                val operandRight = state.cachedOperand?.toDoubleOrNull() ?: state.lastOperand.toDouble()

                if (operandLeft.isNaN() || operandRight.isNaN()) return@to state.copy(hasError = true, errorMessage = "Invalid operand")

                val operation = state.lastOperator as ButtonCalculatorBinary
                val result = engineMath.applyArithmetic(operandLeft, operation, operandRight)

                state.copy(
                    lastOperand = result.toString(),
                    expression = listOf(result.toString(), operation.symbol.label),
                    cachedOperand = operandRight.toString(), // Save the last entered number
                    activeButton = ButtonCalculatorControl.Equals
                )
            }
        )
    }

    private fun applyClearAll(): CalculatorState = CalculatorState()

    private fun applyClear(state: CalculatorState): CalculatorState {
        return state.modifyWith(
            { state.hasError } to { applyClearAll() },
            { state.activeButton == ButtonCalculatorControl.Equals } to { applyClearAll() },
            { state.expression.lastOrNull()?.toDoubleOrNull()?.isNaN() == true } to { applyClearAll() },
            { state.expression.isNotEmpty() } to { state.copy(expression = expression.dropLast(1)) },
            { true } to { applyClearAll() }
        )
    }
}