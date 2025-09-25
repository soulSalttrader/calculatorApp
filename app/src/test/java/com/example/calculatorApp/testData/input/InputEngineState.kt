package com.example.calculatorApp.testData.input

sealed interface InputEngineState : Input {

    data class Binary(
        private val delegate: InputEngineStateDelegate.Operation
    ) : InputEngineState,
        InputEngineStateDelegate.Operation by delegate {
            override fun toString(): String = "InputEngineState.Binary(state=${delegate.context})"
        }

    data class Unary(
        private val delegate: InputEngineStateDelegate.Operation
    ) : InputEngineState,
        InputEngineStateDelegate.Operation by delegate {
        override fun toString(): String = "InputEngineState.Unary(state=${delegate.context})"
    }
}