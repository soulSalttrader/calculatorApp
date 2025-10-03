package com.example.calculatorApp.arguments

import com.example.calculatorApp.model.layout.ElementLayout
import com.example.calculatorApp.model.layout.ElementLayoutPositioning
import com.example.calculatorApp.model.layout.ElementLayoutText
import com.example.calculatorApp.model.layout.button.ButtonLayoutRegular
import com.example.calculatorApp.model.layout.button.ButtonLayoutWide
import com.example.calculatorApp.model.layout.display.DisplayLayoutInput
import com.example.calculatorApp.model.layout.row.RowLayoutStandard
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataElementLayout.buttonLayoutRegularExpected
import com.example.calculatorApp.testData.TestDataElementLayout.buttonLayoutWideExpected
import com.example.calculatorApp.testData.TestDataElementLayout.displayLayoutInputExpected
import com.example.calculatorApp.testData.TestDataElementLayout.rowLayoutStandardExpected
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedLayout
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputLayout

object TestArgumentsCalculatorElementLayout : TestArguments {

    fun provideElementLayoutTestCases(
        elementLayout: ElementLayout,
        factoryInput: (ElementLayout) -> InputLayout<ElementLayout> = ::elementLayoutInput,
        factoryExpected: (ElementLayout) -> ExpectedLayout = ::elementLayoutExpected,
    ): Sequence<TestCase<Input, Expected>> =
        sequence {

            yield(
                TestCase(
                    input = factoryInput(elementLayout),
                    expected = factoryExpected(elementLayout)
                )
            )
        }

    private fun elementLayoutInput(elementLayout: ElementLayout): InputLayout<ElementLayout> =
        when (elementLayout::class) {
            ButtonLayoutRegular::class,
            ButtonLayoutWide::class -> InputLayout.Button(elementLayout)

            DisplayLayoutInput::class -> InputLayout.Display(elementLayout)
            RowLayoutStandard::class -> InputLayout.Row(elementLayout)

            else -> throw IllegalArgumentException("Unknown elementLayout: ${elementLayout::class}")
        }

    private fun elementLayoutExpected(elementLayout: ElementLayout): ExpectedLayout =
        when (elementLayout::class) {
            ButtonLayoutRegular::class -> buttonLayoutRegularExpected
            ButtonLayoutWide::class -> buttonLayoutWideExpected
            DisplayLayoutInput::class -> displayLayoutInputExpected
            RowLayoutStandard::class -> rowLayoutStandardExpected

            else -> throw IllegalArgumentException("Unknown elementLayout: ${elementLayout::class}")
        }

    fun <E> layoutPropertyChecks(): List<Pair<String, (E, ElementLayout) -> Pair<Any?, Any?>>> where E: ExpectedLayout, E: ElementLayout =
        listOf(
            "alignment"  to { expected, actual -> expected.alignment to actual.alignment },
            "modifier"   to { expected, actual -> expected.modifier  to actual.modifier },
            "shape"      to { expected, actual -> expected.shape     to actual.shape },
            "weight"     to { expected, actual -> expected.weight    to actual.weight },
        )

    fun <E> layoutTextPropertyChecks(): List<Pair<String, (E, ElementLayoutText) -> Pair<Any?, Any?>>> where E: ExpectedLayout, E: ElementLayoutText =
        listOf(
            "alignText"  to { expected, actual -> expected.alignText  to actual.alignText },
            "sizeFont"   to { expected, actual -> expected.sizeFont   to actual.sizeFont },
            "weightFont" to { expected, actual -> expected.weightFont to actual.weightFont },
        )

    fun layoutPositioningPropertyChecks(): List<Pair<String, (ExpectedLayout.Row, ElementLayoutPositioning) -> Pair<Any?, Any?>>> =
        listOf(
            "alignmentVertical"     to { expected, actual -> expected.alignmentVertical     to actual.alignmentVertical },
            "arrangementHorizontal" to { expected, actual -> expected.arrangementHorizontal to actual.arrangementHorizontal },
            "arrangementVertical"   to { expected, actual -> expected.arrangementVertical   to actual.arrangementVertical },
        )
}