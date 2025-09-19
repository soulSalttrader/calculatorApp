package com.example.calculatorApp.testData.input

sealed interface InputEngineState : Input {

    data class Binary(
        private val delegate: InputEngineStateDelegate.Binary
    ) : InputEngineState,
        InputEngineStateDelegate.Binary by delegate {
            override fun toString(): String = "InputEngineState.Binary(state=${delegate.context})"
        }
}