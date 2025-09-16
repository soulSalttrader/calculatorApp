package com.example.calculatorApp.testData.input

sealed interface InputEngineMath : Input {

    data class Binary(
        private val delegateInput: InputEval.Binary
    ) : InputEngineMath,
        InputEval.Binary by delegateInput {
            override fun toString(): String = "Binary($leftOperand $operation $rightOperand)"
        }

    data class Percentage(
        private val delegate: InputEval.Percentage
    ) : InputEngineMath,
        InputEval.Percentage by delegate {
            override fun toString(): String = "Percentage($previousNumber ${lastOperator?.symbol?.label} $operand %)"
        }

    data class Sign(
        private val delegate: InputEval.Sign
    ) : InputEngineMath,
        InputEval.Sign by delegate {
            override fun toString(): String = "Sign($operand)"
        }
}