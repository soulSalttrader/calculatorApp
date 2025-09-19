package com.example.calculatorApp.testData.scenario.context

import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.state.HasError
import com.example.calculatorApp.model.state.HasExpression
import com.example.calculatorApp.model.state.HasInteraction

sealed interface ContextEngineState : Context {
    interface Base : HasExpression, HasInteraction

    data class Error(
        override val expression: List<Token>,
        override val lastOperand: String,
        override val lastOperator: Operator?,

        override val activeButton: Button?,

        override val hasError: Boolean,
        override val errorMessage: String?,
    ) : ContextEngineState, Base, HasError
}