package com.example.calculatorApp.testData.scenario

import com.example.calculatorApp.model.state.HasState
import com.example.calculatorApp.testData.expected.ExpectedEngineState
import com.example.calculatorApp.testData.input.InputEngineState
import com.example.calculatorApp.testData.scenario.context.ContextEngineState

sealed interface ScenarioEngineState : Scenario {

    val factoryContext: (HasState) -> ContextEngineState
    val factoryInput: (ContextEngineState) -> InputEngineState.Binary
    val factoryExpected: (ContextEngineState) -> ExpectedEngineState.Binary

    object Error : ScenarioEngineState by ScenarioEngineStateError
}