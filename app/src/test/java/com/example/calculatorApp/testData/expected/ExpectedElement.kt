package com.example.calculatorApp.testData.expected

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.model.symbols.Symbol

sealed interface ExpectedElement : Expected {
    val background: Color
    val foreground: Color
    val category: ElementCategory<ElementColorStyle>

    data class Button(
        override val background: Color,
        override val foreground: Color,
        override val category: ElementCategory<ElementColorStyle>,
        val symbol: Symbol,
        val isPrefix: Boolean? = null,
    ) : ExpectedElement

    data class Display(
        override val background: Color,
        override val foreground: Color,
        override val category: ElementCategory<ElementColorStyle>,
    ) : ExpectedElement

    data class Row(
        override val background: Color,
        override val foreground: Color,
        override val category: ElementCategory<ElementColorStyle>,
        val buttonData: Sequence<ButtonData>,
    ) : ExpectedElement
}