package com.example.calculatorApp.testData.input

sealed interface InputEngineState : Input {

    data class Binary(
        private val delegate: InputEngineStateDelegate.Base
    ) : InputEngineState,
        InputEngineStateDelegate.Base by delegate {
            override fun toString(): String = "InputEngineState.Binary(state=${delegate.context})"
        }

    data class Unary(
        private val delegate: InputEngineStateDelegate.Base
    ) : InputEngineState,
        InputEngineStateDelegate.Base by delegate {
        override fun toString(): String = "InputEngineState.Unary(state=${delegate.context})"
    }

    data class Control(
        private val delegate: InputEngineStateDelegate.Base
    ) : InputEngineState,
        InputEngineStateDelegate.Base by delegate {
        override fun toString(): String = "InputEngineState.Control(state=${delegate.context})"
    }
}