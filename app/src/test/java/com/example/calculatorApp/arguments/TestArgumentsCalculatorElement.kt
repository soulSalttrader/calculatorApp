package com.example.calculatorApp.arguments

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.elements.button.ButtonCategory
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataElementSeq.buttonsBinaryTest
import com.example.calculatorApp.testData.TestDataElementSeq.buttonsControlsTest
import com.example.calculatorApp.testData.TestDataElementSeq.buttonsNumbersTest
import com.example.calculatorApp.testData.TestDataElementSeq.buttonsParenthesisTest
import com.example.calculatorApp.testData.TestDataElementSeq.buttonsUnaryTest
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedElement
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputElement
import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge
import com.example.calculatorApp.ui.theme.White
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

    fun provideElementTestCases(elements: Sequence<Button>): Sequence<TestCase<Input, Expected>> =
        sequence {
            elements.forEach { button ->
                val category = buildButtonCategoriesMap()[button] ?: error("No category found for ${button.javaClass}")
                val background = buildButtonColorsMap()[button]?.first ?: error("No background found for ${button.javaClass}")
                val foreground = buildButtonColorsMap()[button]?.second ?: error("No foreground found for ${button.javaClass}")
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

    private fun buildButtonCategoriesMap(
        baseMappings: ButtonCategoryMapping = buttonCategoryMappingBase,
    ): Map<Button, ButtonCategory<ElementColorStyle>> = buildMap {
        baseMappings.forEach { (buttons, category) ->
            putAll(buttons.associateWith { category })
        }
    }

    val buttonCategoryMappingBase = listOf(
        buttonsBinaryTest to ButtonCategory.Binary,
        buttonsControlsTest to ButtonCategory.Control,
        buttonsUnaryTest to ButtonCategory.Unary,
        buttonsParenthesisTest to ButtonCategory.Parenthesis,
        buttonsNumbersTest to ButtonCategory.Number,
    )

    private fun buildButtonColorsMap(
        baseMappings: ButtonGroupMapping = iButtonStyleMappingBase,
        overrides: Map<out Button, ButtonColors> = iButtonStyleMappingOverrides,
    ): Map<Button, ButtonColors> = buildMap {
        baseMappings.forEach { (buttons, colors) ->
            putAll(buttons.associateWith { colors })
        }
        putAll(overrides)
    }

    val iButtonStyleMappingBase = listOf(
        buttonsBinaryTest to Pair(VividGamboge, White),
        buttonsUnaryTest to Pair(SilverGrey, Onyx),
        buttonsControlsTest to Pair(SilverGrey, Onyx),
        buttonsNumbersTest to Pair(Onyx,White),
        buttonsParenthesisTest to Pair(Onyx, White),
    )

    val iButtonStyleMappingOverrides = mapOf(
        ButtonCalculatorControl.Decimal to Pair(Onyx, White),
        ButtonCalculatorControl.Equals to Pair(VividGamboge, White),
    )
}
