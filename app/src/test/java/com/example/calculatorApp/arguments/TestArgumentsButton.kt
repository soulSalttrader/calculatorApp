package com.example.calculatorApp.arguments

import com.example.calculatorApp.testData.TestDataButtonCalculatorX

object TestArgumentsButton : TestArguments {

    fun <T, R> provideMappedTestData(
        items: Sequence<T>,
        map: Map<T, R>,
    ): Sequence<TestDataButtonCalculatorX<T>> =
        sequence {
            items.forEach { item ->
                yield(
                    TestDataButtonCalculatorX(
                        button = item,
                        expected = map[item],
                    )
                )
            }
        }
}
