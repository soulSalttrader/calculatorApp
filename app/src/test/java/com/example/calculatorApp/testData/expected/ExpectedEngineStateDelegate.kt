package com.example.calculatorApp.testData.expected

import com.example.calculatorApp.testData.scenario.context.ContextEngineState

interface ExpectedEngineStateDelegate : Expected {

    interface Binary : ExpectedEngineStateDelegate {
        val context: ContextEngineState.Base
    }
}