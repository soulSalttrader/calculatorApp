package com.example.calculatorApp.testData.scenario

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.testData.expected.ExpectedEngineState
import com.example.calculatorApp.testData.input.InputEngineState
import com.example.calculatorApp.testData.scenario.context.ContextEngineState

sealed interface ScenarioEngineState : Scenario {

    val buildInput: (ContextEngineState) -> InputEngineState.Binary
    val buildExpected: (ContextEngineState) -> ExpectedEngineState.Binary
    fun buildContexts(expressionInput: List<Token>, lastOperand: String, button: Button): Pair<ContextEngineState, ContextEngineState>

    object Error : ScenarioEngineState by ScenarioEngineStateError
    object Update : ScenarioEngineState by ScenarioEngineStateUpdate
    object Success : ScenarioEngineState by ScenarioEngineStateSuccess
    object Replace : ScenarioEngineState by ScenarioEngineStateReplace
}