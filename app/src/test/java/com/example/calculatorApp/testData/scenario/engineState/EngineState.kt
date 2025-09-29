package com.example.calculatorApp.testData.scenario.engineState

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.testData.expected.ExpectedEngineState
import com.example.calculatorApp.testData.input.InputEngineState
import com.example.calculatorApp.testData.scenario.Scenario
import com.example.calculatorApp.testData.scenario.context.ContextEngineState

sealed interface EngineState : Scenario {
    val buildInput: (ContextEngineState) -> InputEngineState
    val buildExpected: (ContextEngineState) -> ExpectedEngineState
    fun buildContexts(expressionInput: List<Token>, lastOperand: Number, button: Button): Pair<ContextEngineState, ContextEngineState>

    sealed interface Binary : EngineState {
        object Error : EngineState by BinaryError
        object Update : EngineState by BinaryUpdate
        object Success : EngineState by BinarySuccess
        object Replace : EngineState by BinaryReplace
    }

    sealed interface Unary : EngineState {
        object Sign : EngineState by UnarySign
        object SignError : EngineState by UnarySignError
        object PercentageError : EngineState by UnaryPercentageError
        object PercentageOperand : EngineState by UnaryPercentageOperand
        object PercentageAddSub : EngineState by UnaryPercentageAddSub
        object PercentageMulDiv : EngineState by UnaryPercentageMulDiv
    }

    sealed interface Control : EngineState {
        object ClearAll : EngineState by ControlClearAll
        object Clear : EngineState by ControlClear
        object ClearError : EngineState by ControlClearError
        object Decimal : EngineState by ControlDecimal
        object DecimalError : EngineState by ControlDecimalError
        object DecimalZero : EngineState by ControlDecimalZero
        object Equals : EngineState by ControlEquals
        object EqualsError : EngineState by ControlEqualsError
        object EqualsInvalid : EngineState by ControlEqualsInvalid
        object EqualsRepeatable : EngineState by ControlEqualsRepeatable
        object EqualsRepeatableOperand : EngineState by ControlEqualsRepeatableOperand
        object EqualsPercentage : EngineState by ControlEqualsPercentage
    }

    sealed interface Numeric : EngineState {
        object Success : EngineState by NumberSuccess
        object Error : EngineState by NumberError
    }
}