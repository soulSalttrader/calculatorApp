package com.example.calculatorApp.arguments

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataElementSeq.buttonCategoryMappingBase
import com.example.calculatorApp.testData.TestDataElementSeq.iButtonStyleMappingBase
import com.example.calculatorApp.testData.TestDataElementSeq.iButtonStyleMappingOverrides
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedElement
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputElement
import com.example.calculatorApp.testData.input.UIElement
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

    fun provideButtonTestCases(buttons: Sequence<Button>): Sequence<TestCase<Input, Expected>> =
        sequence {
            buttons.forEach { button ->
                val category = buildElementCategoriesMap(buttonCategoryMappingBase)[button] ?: error("No category found for ${button.javaClass}")
                val (background, foreground) = buildElementColorsMap(iButtonStyleMappingBase, iButtonStyleMappingOverrides)[button] ?: error("No colors found for ${button.javaClass}")

                yield(
                    TestCase(
                        InputElement.Button(element = button),
                        ExpectedElement.Button(
                            background = background,
                            foreground = foreground,
                            category = category,
                            symbol = button.symbol,
                            isPrefix = button is ButtonCalculatorUnary.Sign,
                        )
                    )
                )
            }
        }

    private fun <T, R : UIElement> buildElementCategoriesMap(
        baseMappings: List<Pair<Sequence<R>, T>>,
    ): Map<R, T> = buildMap {
        baseMappings.forEach { (elements, value) ->
            putAll(elements.associateWith { value })
        }
    }

    private fun <T, R : UIElement> buildElementColorsMap(
        baseMappings: List<Pair<Sequence<R>, T>>,
        overrides: Map<out R, T> = emptyMap()
    ): Map<R, T> = buildMap {
        baseMappings.forEach { (elements, value) ->
            putAll(elements.associateWith { value })
        }
        putAll(overrides)
    }

    // TODO: investigate why buildButtonMap causes java.lang.NoClassDefFoundError at runtime
    // Seems to happen when using typealiases ButtonCategoryMapping / ButtonGroupMapping
    // Temporary workaround: using category/colors-specific builders instead
    private fun <T, R : UIElement> buildButtonMap(
        baseMappings: List<Pair<Sequence<R>, T>>,
        overrides: Map<out R, T> = emptyMap()
    ): Map<R, T> = buildMap {
        baseMappings.forEach { (buttons, value) ->
            putAll(buttons.associateWith { value })
        }
        putAll(overrides)
    }
}