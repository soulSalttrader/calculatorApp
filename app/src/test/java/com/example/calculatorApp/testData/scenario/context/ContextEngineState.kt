package com.example.calculatorApp.testData.scenario.context

import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.state.HasError
import com.example.calculatorApp.model.state.HasExpression
import com.example.calculatorApp.model.state.HasInteraction
import com.example.calculatorApp.model.state.HasResult

sealed interface ContextEngineState : Context {
    interface Base : HasExpression, HasInteraction, HasResult, HasError

    data class Error(
        override val expression: List<Token>,
        override val lastOperand: String,
        override val lastOperator: Operator?,

        override val activeButton: Button?,

        override val lastResult: String?,
        override val cachedOperand: String?,
        override val isComputed: Boolean,

        override val hasError: Boolean,
        override val errorMessage: String?,
    ) : ContextEngineState, Base

    data class Update(
        override val expression: List<Token>,
        override val lastOperand: String,
        override val lastOperator: Operator?,

        override val activeButton: Button?,

        override val lastResult: String?,
        override val cachedOperand: String?,
        override val isComputed: Boolean,

        override val hasError: Boolean,
        override val errorMessage: String?,
    ) : ContextEngineState, Base

    data class Success(
        override val expression: List<Token>,
        override val lastOperand: String,
        override val lastOperator: Operator?,

        override val activeButton: Button?,

        override val lastResult: String?,
        override val cachedOperand: String?,
        override val isComputed: Boolean,

        override val hasError: Boolean,
        override val errorMessage: String?,
    ) : ContextEngineState, Base

    data class Replace(
        override val expression: List<Token>,
        override val lastOperand: String,
        override val lastOperator: Operator?,

        override val activeButton: Button?,

        override val lastResult: String?,
        override val cachedOperand: String?,
        override val isComputed: Boolean,

        override val hasError: Boolean,
        override val errorMessage: String?,
    ) : ContextEngineState, Base
}