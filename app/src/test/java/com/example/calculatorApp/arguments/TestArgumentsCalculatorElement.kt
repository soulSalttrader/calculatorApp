package com.example.calculatorApp.arguments

import com.example.calculatorApp.testData.TestDataCalculatorElement

object TestArgumentsCalculatorElement : TestArguments {

    fun <T, R> provideMappedTestData(
        items: Sequence<T>,
        map: Map<T, R>,
    ): Sequence<TestDataCalculatorElement<T>> =
        sequence {
            items.forEach { item ->
                yield(
                    TestDataCalculatorElement(
                        element = item,
                        expected = map[item],
                    )
                )
            }
        }
}
