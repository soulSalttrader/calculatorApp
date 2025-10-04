package com.example.calculatorApp.model.elements.button

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.state.CalculatorStateDomain
import com.example.calculatorApp.model.symbols.Symbol
import com.example.calculatorApp.model.symbols.SymbolButton

sealed class ButtonCalculatorNumber(override val symbol: Symbol) : Button {

    data object Zero : ButtonCalculatorNumber(SymbolButton.ZERO)
    data object One : ButtonCalculatorNumber(SymbolButton.ONE)
    data object Two : ButtonCalculatorNumber(SymbolButton.TWO)
    data object Three : ButtonCalculatorNumber(SymbolButton.THREE)
    data object Four : ButtonCalculatorNumber(SymbolButton.FOUR)
    data object Five : ButtonCalculatorNumber(SymbolButton.FIVE)
    data object Six : ButtonCalculatorNumber(SymbolButton.SIX)
    data object Seven : ButtonCalculatorNumber(SymbolButton.SEVEN)
    data object Eight : ButtonCalculatorNumber(SymbolButton.EIGHT)
    data object Nine : ButtonCalculatorNumber(SymbolButton.NINE)

    override fun shouldHighlight(state: CalculatorStateDomain): Boolean = false

    override fun getCategory(): ElementCategory<ElementColorStyle> = ButtonCategory.Number
    override fun getBackgroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).backgroundColor
    override fun getForegroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).foregroundColor

    private fun getStyle(style: ElementCategoryStyleCollection<ElementColorStyle>): ElementColorStyle {
        val categoryStyle = style.categories[getCategory()]
            ?: throw IllegalArgumentException("Category '${getCategory()}' not found.")

        return categoryStyle.specificStyles[symbol.label] ?: categoryStyle.baseStyle
    }
}