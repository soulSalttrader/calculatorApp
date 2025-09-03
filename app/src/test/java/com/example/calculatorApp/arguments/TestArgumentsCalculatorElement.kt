package com.example.calculatorApp.arguments

import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.styles.StylesButton.iButtonStyle
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedElement
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputElement
import kotlin.reflect.KClass

object TestArgumentsCalculatorElement : TestArguments {

    fun <T : Any, R> provideMappedTestData(
        items: Sequence<T>,
        map: Map<KClass<out T>, R>,
    ): Sequence<TestCase<T, R>> =
        sequence {
            items.forEach { item ->
                yield(
                    TestCase(
                        input = item,
                        expected = map[item::class] ?: error("No mapping found for ${item::class.simpleName}"),
                    )
                )
            }
        }

    fun provideElementTestCases(
        elements: Sequence<Button>,
        style: ElementCategoryStyleCollection<ElementColorStyle> = iButtonStyle,
        ): Sequence<TestCase<Input, Expected>> =
            sequence {
                elements.forEach { button ->
                    val category = button.getCategory()
                    val background = button.getBackgroundColor(style)
                    val foreground = button.getForegroundColor(style)
                    val isPrefix = button is ButtonCalculatorUnary.Sign
                    yield(
                        TestCase(
                            InputElement(element = button),
                            ExpectedElement.Button(
                                background = background,
                                foreground = foreground,
                                category = category,
                                symbol = button.symbol,
                                isPrefix = isPrefix,
                            )
                        )
                    )
                }
            }
}
