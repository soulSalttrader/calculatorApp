package com.example.calculatorApp.testData.expected

sealed interface ExpectedEngineState {

    data class Binary(
        private val delegate: ExpectedEngineStateDelegate.Binary
    ) : ExpectedEngineState,
        ExpectedEngineStateDelegate.Binary by delegate {
            override fun toString(): String = "ExpectedEngineState.Binary(state=${delegate.context})"
        }
}