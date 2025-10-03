package com.example.calculatorApp.testData.scenario.engineState

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.testData.scenario.buildNumberExpectedState
import com.example.calculatorApp.testData.scenario.buildNumberInputState
import com.example.calculatorApp.testData.scenario.context.ContextEngineState
import com.example.calculatorApp.testData.scenario.repeatedOperand
import com.example.calculatorApp.utils.Constants.MAX_NUM_LENGTH

object NumberMaxLength : EngineState.Numeric {

    override val buildInput = { context: ContextEngineState -> buildNumberInputState<ContextEngineState.Success>(context) }
    override val buildExpected = { context: ContextEngineState -> buildNumberExpectedState<ContextEngineState.Success>(context) }

    override fun buildContexts(
        expressionInput: List<Token>,
        lastOperand: Number,
        button: Button
    ): Pair<ContextEngineState, ContextEngineState> {
        val input = ContextEngineState.Success(
            expression = expressionInput,
            lastOperand = if (lastOperand == 0) "0" else lastOperand.repeatedOperand(MAX_NUM_LENGTH),
            lastOperator = button.toOperator(),

            activeButton = button,

            lastResult = null,
            cachedOperand = null,
            isComputed = false,

            hasError = false,
            errorMessage = null,
        )

        val expected = input.copy(
            lastOperand = if (lastOperand == 0) "9" else lastOperand.repeatedOperand(MAX_NUM_LENGTH),
            activeButton = ButtonCalculatorNumber.Nine
        )
        return input to expected
    }
}