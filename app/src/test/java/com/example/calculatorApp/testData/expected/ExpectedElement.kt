package com.example.calculatorApp.testData.expected

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.button.ButtonData

import com.example.calculatorApp.model.symbols.SymbolButton

sealed interface ExpectedElement : Expected {
    val background: Color
    val foreground: Color

    data class Button(
        override val background: Color,
        override val foreground: Color,
        val symbol: SymbolButton,
        val isPrefix: Boolean? = null,
    ) : ExpectedElement

    data class Display(
        override val background: Color,
        override val foreground: Color,
    ) : ExpectedElement

    data class Row(
        override val background: Color,
        override val foreground: Color,
        val buttonData: Sequence<ButtonData>,
    ) : ExpectedElement
}