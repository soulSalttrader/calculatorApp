package com.example.calculatorApp.testData.input

sealed interface InputEngineMath : Input {

    data class Binary(
        private val delegateInput: InputEval.Binary
    ) : InputEngineMath,
        InputEval.Binary by delegateInput

    data class Percentage(
        private val delegate: InputEval.Percentage
    ) : InputEngineMath,
        InputEval.Percentage by delegate

    data class Sign(
        private val delegate: InputEval.Sign
    ) : InputEngineMath,
        InputEval.Sign by delegate
}