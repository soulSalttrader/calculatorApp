package com.example.calculatorApp.model.elements.button

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.symbols.Symbol
import com.example.calculatorApp.model.symbols.SymbolButton

sealed class ButtonCalculatorBinary(override val symbol: Symbol) : Button {

    data object Addition : ButtonCalculatorBinary(SymbolButton.ADDITION)
    data object Subtraction : ButtonCalculatorBinary(SymbolButton.SUBTRACTION)
    data object Multiplication : ButtonCalculatorBinary(SymbolButton.MULTIPLICATION)
    data object Division : ButtonCalculatorBinary(SymbolButton.DIVISION)

    override fun getCategory(): ElementCategory<ElementColorStyle> = ButtonCategory.Arithmetic
    override fun getBackgroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).backgroundColor
    override fun getTextColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).textColor

    private fun getStyle(style: ElementCategoryStyleCollection<ElementColorStyle>): ElementColorStyle {
        val categoryStyle = style.categories[getCategory()]
            ?: throw IllegalArgumentException("Category '${getCategory()}' not found.")

        return categoryStyle.specificStyles[symbol.label] ?: categoryStyle.baseStyle
    }
}