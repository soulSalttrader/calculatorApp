package com.example.calculatorApp.arguments

import com.example.calculatorApp.annotations.ConceptClass
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.ElementColorStyleImpl
import com.example.calculatorApp.model.elements.display.Display
import com.example.calculatorApp.model.elements.display.DisplayCalculatorInput
import com.example.calculatorApp.model.styles.StylesDisplay
import com.example.calculatorApp.ui.theme.Black
import com.example.calculatorApp.ui.theme.White
import com.example.calculatorApp.utils.DisplayCalculatorList
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream

object TestArgumentsDisplayStyleBuilder : TestArguments {

    fun provideDisplayStyle(style: ElementCategoryStyleCollection<ElementColorStyle>? = null): Stream<Arguments> {
        val inputBaseStyle = ElementColorStyleImpl(backgroundColor = Black, foregroundColor = White)
        val scientificStyle = ElementColorStyleImpl(backgroundColor = Black, foregroundColor = White)

        val testedStyle = style ?: StylesDisplay.iDisplayStyleInput

        val expectedStyles = buildMap<Display, ElementColorStyle> {
            putAll(DisplayCalculatorList.inputs.associateWith{ inputBaseStyle })

            // base style overridden for control buttons
            put(@OptIn(ConceptClass::class) DisplayCalculatorInput.Scientific, scientificStyle)
        }

        return DisplayCalculatorList.inputs.map { button ->
            val expectedStyle = expectedStyles[button]
            Arguments.of(button, testedStyle, expectedStyle)
        }.stream()
    }
}