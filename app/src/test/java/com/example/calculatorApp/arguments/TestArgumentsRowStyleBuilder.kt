package com.example.calculatorApp.arguments

import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.ElementColorStyleImpl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.styles.StylesRow
import com.example.calculatorApp.ui.theme.Black
import com.example.calculatorApp.ui.theme.White
import com.example.calculatorApp.utils.ButtonCalculatorList
import com.example.calculatorApp.utils.RowCalculatorList.allRows
import com.example.calculatorApp.utils.RowCalculatorList.standards
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream
import kotlin.streams.asStream

object TestArgumentsRowStyleBuilder : TestArguments {

    fun provideRowStyle(style: ElementCategoryStyleCollection<ElementColorStyle>? = null): Stream<Arguments> {
        val standardBaseStyle = ElementColorStyleImpl(backgroundColor = Black, textColor = White)

        val testedStyle = style ?: StylesRow.iRowStyle

        val expectedStyles = sequenceOf(
            allRows.map { it to standardBaseStyle },
        ).flatMap { it }
            .toMap()

        return allRows.map { row ->
            val expectedStyle = expectedStyles[row] ?: standardBaseStyle
            Arguments.of(row, testedStyle, expectedStyle)
        }.asStream()
    }
}