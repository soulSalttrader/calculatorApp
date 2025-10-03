package com.example.calculatorApp.testData.expected

import com.example.calculatorApp.testData.scenario.context.ContextEngineState

interface ExpectedEngineStateDelegate : Expected {

    interface Base : ExpectedEngineStateDelegate {
        val context: ContextEngineState.Base
    }
}