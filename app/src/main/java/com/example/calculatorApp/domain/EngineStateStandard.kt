package com.example.calculatorApp.domain

import com.example.calculatorApp.domain.ast.EvaluationResult
import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.Parser
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.Token.Companion.lastNumberOrNull
import com.example.calculatorApp.domain.ast.TokenizerUtils.toBinaryOperator
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.state.CalculatorStateDomain
import com.example.calculatorApp.utils.Constants.MAX_NUM_LENGTH

class EngineStateStandard(
    private val engineMath: EngineMath,
    private val engineNode: EngineNode,
    private val parser: Parser,
) : EngineState {

    override fun handleBinary(state: CalculatorStateDomain, binary: ButtonCalculatorBinary): CalculatorStateDomain {
        return state.modifyWith(
            { true } to { enterBinary(state, binary.toBinaryOperator()) }
        )
    }

    override fun handleUnary(state: CalculatorStateDomain, unary: ButtonCalculatorUnary): CalculatorStateDomain {
        return state.modifyWith(
            { state.hasError } to { this },
            { true } to {
                val lastInput = state.lastOperand.toDoubleOrNull() ?: state.expression.lastNumberOrNull()?.value
                ?: return@to state.copy(hasError = true, errorMessage = "Invalid number")
                val previousNumber = state.expression.lastNumberOrNull()?.value
                val operator = state.lastOperator

                val newValue = when (unary) {
                    ButtonCalculatorUnary.Sign -> engineMath.evalSign(lastInput)
                    ButtonCalculatorUnary.Percentage -> engineMath.evalPercent(lastInput, previousNumber, operator)
                }

                state.copy(lastOperand = newValue.value.toString())
            }
        )
    }

    override fun handleControl(state: CalculatorStateDomain, control: ButtonCalculatorControl): CalculatorStateDomain {
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

    override fun handleNumber(state: CalculatorStateDomain, number: ButtonCalculatorNumber): CalculatorStateDomain {
        return state.modifyWith(
            { state.hasError } to { this },
            { state.lastOperand == "NaN" } to { this },
            { state.lastOperand == "0" } to { state.copy(lastOperand = number.symbol.label) },
            { state.lastOperand.length >= MAX_NUM_LENGTH } to { this },
            { true } to {
                state.copy(lastOperand = state.lastOperand + number.symbol.label, lastResult = null)
            }
        )
    }

    private fun enterDecimal(state: CalculatorStateDomain): CalculatorStateDomain {
        return state.modifyWith(
            { state.hasError } to { this },
            { state.lastOperand == "NaN" } to { this },
            { !state.lastOperand.contains(".") && state.lastOperand.isNotBlank() } to { state.copy(lastOperand = state.lastOperand  + ".") },
            { !state.lastOperand.contains(".") && state.lastOperand.isBlank() } to { state.copy(lastOperand = "0" + ".") }
        ).also { println(it) }
    }

    private fun applyClear(state: CalculatorStateDomain): CalculatorStateDomain {
        return state.modifyWith(
            { state.hasError } to { applyClearAll() },
            { state.activeButton == ButtonCalculatorControl.Equals } to { applyClearAll() },
            { state.lastOperand.isNotBlank() } to { state.copy(lastOperand = "0") },
            { true } to { applyClearAll() }
        )
    }

    private fun applyClearAll(): CalculatorStateDomain {
        return CalculatorStateDomain()
    }

    private fun applyEquals(state: CalculatorStateDomain): CalculatorStateDomain {
        return state.modifyWith(
            { state.hasError } to { this },
            { state.expression.isEmpty() && state.lastOperand.isNotBlank() } to { state.copy(lastOperand = "", lastResult = state.lastOperand) },
            { state.lastOperand.isNotBlank() && state.cachedOperand != null } to {
                val lastOperator = extractBinaryOperator(state) ?: return@to state.copy(hasError = true, errorMessage = "No last operator I.")
                val newState = state.copy(
                    expression = listOf(Token.Number(state.lastOperand.toDouble()), lastOperator),
                )

                val operand = state.cachedOperand?.toDouble() ?: 0.0
                val newTokens = buildTokenList(newState.expression, operand)
                val ast = parser.parse(newState.copy(expression = newTokens).expression)
                val result = engineNode.evaluate(ast)

                assessState(result, newState, lastOperator, operand)
            },
            { true } to {
                val operand = extractOperand(state)
                val lastOperator = extractBinaryOperator(state) ?: return@to state.copy(hasError = true, errorMessage = "No last operator II.")
                val newTokens = buildTokenList(state.expression, operand)
                val ast = parser.parse(state.copy(expression = newTokens).expression)
                val result = engineNode.evaluate(ast)

                assessState(result, state, lastOperator, operand)
            }
        )
    }

    private fun enterBinary(state: CalculatorStateDomain, binary: OperatorBinary): CalculatorStateDomain {
        return state.modifyWith(
            { state.hasError } to { this },
            { true } to {
                val operand = state.lastOperand.toDoubleOrNull() ?: lastResult?.toDouble()
                val newTokens = buildTokenList(state.expression, operand, binary)
                state.copy(expression = newTokens, lastOperand = "", lastOperator = binary, cachedOperand = null)
            }
        )
    }

    private fun assessState(
        result: EvaluationResult,
        state: CalculatorStateDomain,
        lastOperator: Token.Binary,
        operand: Double
    ) = result
        .takeIf { isValidResult(result, { !it.isNaN() }) }
        ?.let { updateStateWithResult(result, state, lastOperator, operand) }
        ?: handleComputationError(state, "Error") // Division by zero

    private fun handleComputationError(state: CalculatorStateDomain, errorMessage: String? = null): CalculatorStateDomain {
        return state.copy(
            lastResult = null,
            lastOperand = "",
            expression = emptyList(),
            isComputed = true,
            hasError = true,
            errorMessage = errorMessage
        )
    }

    private fun updateStateWithResult(
        result: EvaluationResult,
        state: CalculatorStateDomain,
        lastOperator: Token.Binary,
        operand: Double
    ): CalculatorStateDomain {
        return state.copy(
            lastResult = null,
            lastOperand = "",
            expression = listOf(Token.Number(result.value.toDouble()), lastOperator),
            isComputed = true,
            cachedOperand = operand.toString()
        )
    }

    private fun isValidResult(result: EvaluationResult, vararg conditions: (Double) -> Boolean): Boolean {
        val value = result.value.toDouble()
        return conditions.all { it(value) }
    }

    private fun extractOperand(state: CalculatorStateDomain): Double {
        val lastInput = state.lastOperand.toDoubleOrNull()
        val subResult = state.cachedOperand?.toDoubleOrNull() ?: lastInput
        return lastInput ?: subResult ?: state.expression.lastNumberOrNull()?.value ?: 0.0
    }

    private fun extractBinaryOperator(state: CalculatorStateDomain): Token.Binary? {
        return state.lastOperator?.let { Token.Binary(it as OperatorBinary) }
    }

    private fun List<Token>.withOperand(operand: Double?): List<Token> = buildList {
        addAll(this@withOperand)
        operand?.let { add(Token.Number(it)) }
    }

    private fun List<Token>.withOperator(operator: Operator?): List<Token> = buildList {
        addAll(this@withOperator)

        operator?.let { nonNullOperator ->
            when (nonNullOperator) {
                is OperatorBinary -> {
                    if (lastOrNull() is Token.Binary) removeAt(lastIndex)
                    add(Token.Binary(nonNullOperator))
                }

                else -> throw IllegalArgumentException("Unknown operator")
            }
        }
    }

    private fun buildTokenList(existingTokens: List<Token>, operand: Double?, operator: Operator? = null): List<Token> {
        return existingTokens
            .withOperand(operand)
            .withOperator(operator)
    }
}