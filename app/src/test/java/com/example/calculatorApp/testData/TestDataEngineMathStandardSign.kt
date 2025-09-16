package com.example.calculatorApp.testData

import com.example.calculatorApp.testData.expected.ExpectedEngineMath
import com.example.calculatorApp.testData.expected.ExpectedEngineMathResult
import com.example.calculatorApp.testData.input.InputEngineMath
import com.example.calculatorApp.testData.input.InputEval

object TestDataEngineMathStandardSign {

    fun engineMathSignInput(number: Number): InputEngineMath.Sign = InputEngineMath.Sign(
        delegate = object : InputEval.Sign {
            override val operand: Number = number
        }
    )

    fun engineMathSignExpected(number: Number): ExpectedEngineMath.Sign = ExpectedEngineMath.Sign(
        delegateExpected = ExpectedEngineMathResult.normalizeResultTest(-number)
    )

    operator fun Number.unaryMinus(): Number = when (this) {
        is Long -> -this
        is Int -> -this
        is Double -> -this
        is Float -> -this
        else -> error("Unsupported Number subtype: ${this::class}")
    }
}