package com.example.calculatorApp.testData.expected

sealed interface ExpectedEngineState : Expected {

    data class Binary(
        private val delegate: ExpectedEngineStateDelegate.Base
    ) : ExpectedEngineState,
        ExpectedEngineStateDelegate.Base by delegate {
            override fun toString(): String = "ExpectedEngineState.Binary(state=${delegate.context})"
        }

    data class Unary(
        private val delegate: ExpectedEngineStateDelegate.Base
    ) : ExpectedEngineState,
        ExpectedEngineStateDelegate.Base by delegate {
        override fun toString(): String = "ExpectedEngineState.Unary(state=${delegate.context})"
    }
}