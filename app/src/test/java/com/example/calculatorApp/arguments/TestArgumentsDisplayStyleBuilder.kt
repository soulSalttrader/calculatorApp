package com.example.calculatorApp.arguments

import com.example.calculatorApp.annotations.ConceptClass
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.ElementColorStyleImpl
import com.example.calculatorApp.model.elements.display.Display
import com.example.calculatorApp.model.elements.display.DisplayCalculatorInput
import com.example.calculatorApp.model.styles.StylesDisplay
import com.example.calculatorApp.testData.TestDataElement.displaysInputsTest
import com.example.calculatorApp.ui.theme.Black
import com.example.calculatorApp.ui.theme.White
import org.junit.jupiter.params.provider.Arguments

object TestArgumentsDisplayStyleBuilder : TestArguments {

    fun provideDisplayStyle(style: ElementCategoryStyleCollection<ElementColorStyle>? = null): Sequence<Arguments> {
        val inputBaseStyle = ElementColorStyleImpl(backgroundColor = Black, foregroundColor = White)
        val scientificStyle = ElementColorStyleImpl(backgroundColor = Black, foregroundColor = White)

        val testedStyle = style ?: StylesDisplay.iDisplayStyleInput

        val expectedStyles = buildMap<Display, ElementColorStyle> {
            putAll(displaysInputsTest.associateWith{ inputBaseStyle })

            // base style overridden for control buttons
            put(@OptIn(ConceptClass::class) DisplayCalculatorInput.Scientific, scientificStyle)
        }

        return displaysInputsTest.map { button ->
            val expectedStyle = expectedStyles[button]
            Arguments.of(button, testedStyle, expectedStyle)
        }
    }
}