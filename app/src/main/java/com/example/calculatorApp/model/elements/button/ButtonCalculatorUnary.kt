package com.example.calculatorApp.model.elements.button

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.symbols.Symbol
import com.example.calculatorApp.model.symbols.SymbolButton.*

sealed class ButtonCalculatorUnary (
    override val symbol: Symbol,
) : Button {

    data object Sign : ButtonCalculatorUnary(SIGN)
    data object Percentage : ButtonCalculatorUnary(PERCENTAGE)

    fun isPrefix(): Boolean = this in listOf(Sign)
    fun isSuffix(): Boolean = this in listOf(Percentage)

    override fun getCategory(): ElementCategory<ElementColorStyle> = ButtonCategory.Unary
    override fun getBackgroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).backgroundColor
    override fun getTextColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).textColor

    private fun getStyle(style: ElementCategoryStyleCollection<ElementColorStyle>): ElementColorStyle {
        val categoryStyle = style.categories[getCategory()]
            ?: throw IllegalArgumentException("Category '${getCategory()}' not found.")

        return categoryStyle.specificStyles[symbol.label] ?: categoryStyle.baseStyle
    }
}