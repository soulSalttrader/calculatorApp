package com.example.calculatorApp.testData.input

import com.example.calculatorApp.testData.scenario.context.ContextEngineState

sealed interface InputEngineStateDelegate : Input {

    interface Operation : InputEngineStateDelegate  {
        val context: ContextEngineState.Base
    }
}