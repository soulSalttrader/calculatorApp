package com.example.calculatorApp.arguments

import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.ElementColorStyleImpl
import com.example.calculatorApp.model.styles.StylesRow
import com.example.calculatorApp.ui.theme.Black
import com.example.calculatorApp.ui.theme.White
import com.example.calculatorApp.utils.RowCalculatorList
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream

object TestArgumentsRowStyleBuilder : TestArguments {

    fun provideRowStyle(style: ElementCategoryStyleCollection<ElementColorStyle>? = null): Stream<Arguments> {
        val standardBaseStyle = ElementColorStyleImpl(backgroundColor = Black, textColor = White)

        val testedStyle = style ?: StylesRow.iRowStyle

        val expectedStyles = mapOf(
            *RowCalculatorList.standards.map { it to standardBaseStyle }.toTypedArray(),
        )

        return RowCalculatorList.standards.map { button ->
            val expectedStyle = expectedStyles[button]
            Arguments.of(button, testedStyle, expectedStyle)
        }.stream()
    }
}