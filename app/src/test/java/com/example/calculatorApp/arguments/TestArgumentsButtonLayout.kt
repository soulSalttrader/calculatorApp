package com.example.calculatorApp.arguments

import com.example.calculatorApp.model.layout.ElementLayout
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedLayout
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputLayout

object TestArgumentsButtonLayout : TestArguments {

    fun provideElementLayoutTestCases(
        elementLayout: ElementLayout,
        factoryInput: (ElementLayout) -> InputLayout<ElementLayout>,
        factoryExpected: (ElementLayout) -> ExpectedLayout,
    ): Sequence<TestCase<Input, Expected>> =
        sequence {

            yield(
                TestCase(
                    input = factoryInput(elementLayout),
                    expected = factoryExpected(elementLayout)
                )
            )
        }
}