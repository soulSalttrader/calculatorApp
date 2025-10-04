package com.example.calculatorApp.model.elements.button

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.state.CalculatorStateDomain
import com.example.calculatorApp.model.symbols.Symbol
import com.example.calculatorApp.model.symbols.SymbolButton

sealed class ButtonCalculatorBinary(override val symbol: Symbol) : Button {

    data object Addition : ButtonCalculatorBinary(SymbolButton.ADDITION)

    data object Subtraction : ButtonCalculatorBinary(SymbolButton.SUBTRACTION)

    data object Multiplication : ButtonCalculatorBinary(SymbolButton.MULTIPLICATION)

    data object Division : ButtonCalculatorBinary(SymbolButton.DIVISION)

    override fun shouldHighlight(state: CalculatorStateDomain): Boolean = state.activeButton?.let { it == this } ?: false

    override fun getCategory(): ElementCategory<ElementColorStyle> = ButtonCategory.Binary
    override fun getBackgroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).backgroundColor
    override fun getForegroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).foregroundColor

    override fun getStyle(style: ElementCategoryStyleCollection<ElementColorStyle>): ElementColorStyle {
        val categoryStyle = style.categories[getCategory()]
            ?: throw IllegalArgumentException("Category '${getCategory()}' not found.")

        return categoryStyle.specificStyles[symbol.label] ?: categoryStyle.baseStyle
    }
}