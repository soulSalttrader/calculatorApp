package com.example.calculatorApp.arguments

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.annotations.ConceptClass
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.elements.display.Display
import com.example.calculatorApp.model.elements.row.Row
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataElementSeq.buttonCategoryMappingBase
import com.example.calculatorApp.testData.TestDataElementSeq.displayCategoryMappingBase
import com.example.calculatorApp.testData.TestDataElementSeq.iButtonStyleMappingBase
import com.example.calculatorApp.testData.TestDataElementSeq.iButtonStyleMappingOverrides
import com.example.calculatorApp.testData.TestDataElementSeq.iDisplayStyleMappingBase
import com.example.calculatorApp.testData.TestDataElementSeq.iDisplayStyleMappingOverrides
import com.example.calculatorApp.testData.TestDataElementSeq.iRowStyleMappingBase
import com.example.calculatorApp.testData.TestDataElementSeq.rowCategoryMappingBase
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedElement
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputElement
import com.example.calculatorApp.testData.input.UIElement
import kotlin.reflect.KClass

object TestArgumentsCalculatorElement : TestArguments {

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

    @OptIn(ConceptClass::class)
    fun provideDisplayTestCases(displays: Sequence<Display>): Sequence<TestCase<Input, Expected>> =
        provideElementTestCases(
            elements = displays,
            categoryMap = buildElementCategoriesMap(displayCategoryMappingBase),
            colorMap = buildElementColorsMap(iDisplayStyleMappingBase, iDisplayStyleMappingOverrides)
        ) { display, category, background, foreground ->

            TestCase(
                InputElement.Display(element = display),
                ExpectedElement.Display(
                    background = background,
                    foreground = foreground,
                    category = category,
                )
            )
        }

    fun provideRowTestCases(rows: Sequence<Row>): Sequence<TestCase<Input, Expected>> =
        provideElementTestCases(
            elements = rows,
            categoryMap = buildElementCategoriesMap(rowCategoryMappingBase),
            colorMap = buildElementColorsMap(iRowStyleMappingBase)
        ) { row, category, background, foreground ->

            TestCase(
                InputElement.Row(element = row),
                ExpectedElement.Row(
                    background = background,
                    foreground = foreground,
                    category = category,
                    buttonData = row.buttons,
                )
            )
        }

    private fun <E : UIElement> provideElementTestCases(
        elements: Sequence<E>,
        categoryMap: Map<KClass<out E>, ElementCategory<ElementColorStyle>>,
        colorMap: Map<KClass<out E>, Pair<Color, Color>>,
        factory: (E, ElementCategory<ElementColorStyle>, Color, Color) -> TestCase<Input, Expected>
    ): Sequence<TestCase<Input, Expected>> =
        sequence {
            elements.forEach { element ->
                val category = categoryMap[element::class] ?: error("No category found for ${element::class}")
                val (background, foreground) = colorMap[element::class] ?: error("No colors found for ${element ::class}")

                yield(factory(element, category, background, foreground))
            }
        }

    private fun <T, R : UIElement> buildElementCategoriesMap(
        baseMappings: List<Pair<Sequence<KClass<out R>>, T>>,
    ): Map<KClass<out R>, T> = buildMap {
        baseMappings.forEach { (classes, value) ->
            putAll(classes.associateWith { value })
        }
    }

    private fun <T, R : UIElement> buildElementColorsMap(
        baseMappings: List<Pair<Sequence<KClass<out R>>, T>>,
        overrides: Map<KClass<out R>, T> = emptyMap()
    ): Map<KClass<out R>, T> = buildMap {
        baseMappings.forEach { (classes, value) ->
            putAll(classes.associateWith { value })
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