package com.example.calculatorApp.model.elements.button

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.state.CalculatorStateDomain
import com.example.calculatorApp.model.symbols.Symbol
import com.example.calculatorApp.model.symbols.SymbolButton

sealed class ButtonCalculatorParenthesis (
    override val symbol: Symbol,
) : Button {

    data object OpenParenthesis : ButtonCalculatorParenthesis(SymbolButton.OPEN_PARENT)
    data object CloseParenthesis : ButtonCalculatorParenthesis(SymbolButton.CLOSE_PARENT)

    override fun shouldHighlight(state: CalculatorStateDomain): Boolean = false

    override fun getCategory(): ElementCategory<ElementColorStyle> = ButtonCategory.Parenthesis
    override fun getBackgroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).backgroundColor
    override fun getForegroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).foregroundColor

    private fun getStyle(style: ElementCategoryStyleCollection<ElementColorStyle>): ElementColorStyle {
        val categoryStyle = style.categories[getCategory()]
            ?: throw IllegalArgumentException("Category '${getCategory()}' not found.")

        return categoryStyle.specificStyles[symbol.label] ?: categoryStyle.baseStyle
    }
}