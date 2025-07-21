package com.example.calculatorApp.model.elements.button

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.symbols.Symbol
import com.example.calculatorApp.model.symbols.SymbolButton

sealed class ButtonCalculatorControl(override val symbol: Symbol) : Button {

    data object AllClear : ButtonCalculatorControl(SymbolButton.ALL_CLEAR)
    data object Clear : ButtonCalculatorControl(SymbolButton.CLEAR)
    data object Decimal : ButtonCalculatorControl(SymbolButton.DECIMAL)
    data object Equals : ButtonCalculatorControl(SymbolButton.EQUALS)

    override fun getCategory(): ElementCategory<ElementColorStyle> = ButtonCategory.Control
    override fun getBackgroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).backgroundColor
    override fun getForegroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).foregroundColor

    private fun getStyle(style: ElementCategoryStyleCollection<ElementColorStyle>): ElementColorStyle {
        val categoryStyle = style.categories[getCategory()]
            ?: throw IllegalArgumentException("Category '${getCategory()}' not found.")

        return categoryStyle.specificStyles[symbol.label] ?: categoryStyle.baseStyle
    }
}