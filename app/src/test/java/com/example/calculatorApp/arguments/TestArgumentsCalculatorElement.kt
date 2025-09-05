package com.example.calculatorApp.arguments

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.elements.button.ButtonCategory
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataElementSeq.buttonCategoryMappingBase
import com.example.calculatorApp.testData.TestDataElementSeq.iButtonStyleMappingBase
import com.example.calculatorApp.testData.TestDataElementSeq.iButtonStyleMappingOverrides
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedElement
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputElement
import kotlin.reflect.KClass

typealias ButtonCategoryMapping = List<Pair<Sequence<Button>, ButtonCategory<ElementColorStyle>>>
typealias ButtonColors = Pair<Color, Color>
typealias ButtonGroupMapping = List<Pair<Sequence<Button>, ButtonColors>>

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
                val category = buildButtonCategoriesMap()[button] ?: error("No category found for ${button.javaClass}")
                val background = buildButtonColorsMap()[button]?.first ?: error("No background found for ${button.javaClass}")
                val foreground = buildButtonColorsMap()[button]?.second ?: error("No foreground found for ${button.javaClass}")
                val isPrefix = button is ButtonCalculatorUnary.Sign
                yield(
                    TestCase(
                        InputElement.Button(element = button),
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

    private fun buildButtonCategoriesMap(
        baseMappings: ButtonCategoryMapping = buttonCategoryMappingBase,
    ): Map<Button, ButtonCategory<ElementColorStyle>> = buildMap {
        baseMappings.forEach { (buttons, category) ->
            putAll(buttons.associateWith { category })
        }
    }

    private fun buildButtonColorsMap(
        baseMappings: ButtonGroupMapping = iButtonStyleMappingBase,
        overrides: Map<out Button, ButtonColors> = iButtonStyleMappingOverrides,
    ): Map<Button, ButtonColors> = buildMap {
        baseMappings.forEach { (buttons, colors) ->
            putAll(buttons.associateWith { colors })
        }
        putAll(overrides)
    }
}