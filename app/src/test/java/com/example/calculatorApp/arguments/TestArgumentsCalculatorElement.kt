package com.example.calculatorApp.arguments

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementColorStyle
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
        provideElementTestCases(
            elements = buttons,
            categoryMap = buildElementCategoriesMap(buttonCategoryMappingBase),
            colorMap = buildElementColorsMap(iButtonStyleMappingBase, iButtonStyleMappingOverrides)
        ) { button, category, background, foreground ->

            TestCase(
                InputElement.Button(button),
                ExpectedElement.Button(
                    background = background,
                    foreground = foreground,
                    category = category,
                    symbol = button.symbol,
                    isPrefix = button is ButtonCalculatorUnary.Sign
                )
            )
        }

    fun <E : UIElement> provideElementTestCases(
        elements: Sequence<E>,
        categoryMap: Map<UIElement, ElementCategory<ElementColorStyle>>,
        colorMap: Map<UIElement, Pair<Color, Color>>,
        factory: (E, ElementCategory<ElementColorStyle>, Color, Color) -> TestCase<Input, Expected>
    ): Sequence<TestCase<Input, Expected>> =
        sequence {
            elements.forEach { element ->
                val category = categoryMap[element] ?: error("No category found for ${element.javaClass}")
                val (background, foreground) = colorMap[element] ?: error("No colors found for ${element.javaClass}")
                yield(
                    factory(element, category, background, foreground)
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