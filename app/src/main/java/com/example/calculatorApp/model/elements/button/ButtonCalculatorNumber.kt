package com.example.calculatorApp.model.elements.button

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.symbols.Symbol
import com.example.calculatorApp.model.symbols.SymbolButton

sealed class ButtonCalculatorNumber(override val symbol: Symbol) : Button {

    data object Zero : ButtonCalculatorNumber(SymbolButton.DIGIT_0)
    data object One : ButtonCalculatorNumber(SymbolButton.DIGIT_1)
    data object Two : ButtonCalculatorNumber(SymbolButton.DIGIT_2)
    data object Three : ButtonCalculatorNumber(SymbolButton.DIGIT_3)
    data object Four : ButtonCalculatorNumber(SymbolButton.DIGIT_4)
    data object Five : ButtonCalculatorNumber(SymbolButton.DIGIT_5)
    data object Six : ButtonCalculatorNumber(SymbolButton.DIGIT_6)
    data object Seven : ButtonCalculatorNumber(SymbolButton.DIGIT_7)
    data object Eight : ButtonCalculatorNumber(SymbolButton.DIGIT_8)
    data object Nine : ButtonCalculatorNumber(SymbolButton.DIGIT_9)

    override fun getCategory(): ElementCategory<ElementColorStyle> = ButtonCategory.Number
    override fun getBackgroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).backgroundColor
    override fun getTextColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).textColor

    private fun getStyle(style: ElementCategoryStyleCollection<ElementColorStyle>): ElementColorStyle {
        val categoryStyle = style.categories[getCategory()]
            ?: throw IllegalArgumentException("Category '${getCategory()}' not found.")

        return categoryStyle.specificStyles[symbol.label] ?: categoryStyle.baseStyle
    }
}