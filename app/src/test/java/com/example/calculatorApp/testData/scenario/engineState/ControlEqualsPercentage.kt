package com.example.calculatorApp.testData.scenario.engineState

import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.Token.Companion.firstNumberOrNull
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.symbols.SymbolButtonUtils.toButton
import com.example.calculatorApp.testData.expected.ExpectedEngineMathResult
import com.example.calculatorApp.testData.scenario.buildControlExpectedState
import com.example.calculatorApp.testData.scenario.buildControlInputState
import com.example.calculatorApp.testData.scenario.context.ContextEngineState
import com.example.calculatorApp.testData.scenario.lastDigit
import com.example.calculatorApp.testData.scenario.toFunction

object ControlEqualsPercentage : EngineState.Control {

    override val buildInput =
        { context: ContextEngineState -> buildControlInputState<ContextEngineState.Success>(context) }
    override val buildExpected =
        { context: ContextEngineState -> buildControlExpectedState<ContextEngineState.Success>(context) }

    override fun buildContexts(
        expressionInput: List<Token>,
        lastOperand: Number,
        button: Button,
    ): Pair<ContextEngineState, ContextEngineState> {

        val input = ContextEngineState.Success(
            expression = expressionInput,
            lastOperand = lastOperand.toString(),
            lastOperator = button.toOperator(),

            activeButton = lastOperand.lastDigit().toString().toButton(),

            lastResult = null,
            cachedOperand = null,
            isComputed = false,

            hasError = false,
            errorMessage = null,
        )

        val lastOperand = lastOperand.toDouble()
        val operator = button.toOperator() as OperatorBinary
        val initialValue = expressionInput.firstNumberOrNull()?.value ?: throw IllegalArgumentException("Require a non-null initial value")

        val percentage = ExpectedEngineMathResult.evalPercentTest(lastOperand,initialValue,operator).value.toDouble()

        val newValue = operator.toFunction()(initialValue, percentage).also { println("new value $it") }

        val expected = input.copy(
            expression = listOf(
                Token.Number(newValue),
                Token.Binary(operator)
            ),
            lastOperand = "",
            lastOperator = operator,

            activeButton = ButtonCalculatorControl.Equals,

            cachedOperand = percentage.toString(),
            isComputed = true,
        )
        return input to expected
    }
}