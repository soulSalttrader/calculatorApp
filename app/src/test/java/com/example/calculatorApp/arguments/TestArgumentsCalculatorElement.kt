package com.example.calculatorApp.arguments

import com.example.calculatorApp.testData.TestDataCalculatorElement
import kotlin.reflect.KClass

object TestArgumentsCalculatorElement : TestArguments {

    fun <T : Any, R> provideMappedTestData(
        items: Sequence<T>,
        map: Map<KClass<out T>, R>,
    ): Sequence<TestDataCalculatorElement<T, R>> =
        sequence {
            items.forEach { item ->
                yield(
                    TestDataCalculatorElement(
                        element = item,
                        expected = map[item::class] ?: error("No mapping found for ${item::class.simpleName}"),
                    )
                )
            }
        }
}
