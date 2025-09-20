package com.example.calculatorApp.testData

import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.state.HasState
import com.example.calculatorApp.testData.scenario.ScenarioEngineState

object TestDataEngineStateStandard {

    fun buildTokensFrom(
        previousOperand: Number,
        button: Button
    ): List<Token> = listOf(
        Token.Number(previousOperand.toDouble()),
        Token.Binary(button.toOperator() as OperatorBinary)
    )

    fun buildHasState(
        expression: List<Token>,
        lastOperand: String,
        lastOperator: Operator?,
        button: Button,
        scenario: ScenarioEngineState
    ): HasState = object : HasState {
        override val expression = expression
        override val lastOperand = lastOperand
        override val lastOperator = lastOperator

        override val activeButton = button
        override val hasError = scenario is ScenarioEngineState.Error
        override val errorMessage = if (hasError) "Error" else null

        override val cachedOperand: String? = null
        override val isComputed = scenario !is ScenarioEngineState.Error
        override val lastResult: String? = null
    }
}