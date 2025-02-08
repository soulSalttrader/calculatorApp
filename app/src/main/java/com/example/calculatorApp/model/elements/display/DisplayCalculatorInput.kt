package com.example.calculatorApp.model.elements.display

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.annotations.ConceptClass
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle

sealed class DisplayCalculatorInput : Display {

    data object Standard : DisplayCalculatorInput()

    @ConceptClass
    data object Scientific : DisplayCalculatorInput()

    override fun getCategory(): ElementCategory<ElementColorStyle> = DisplayCategory.Input
    override fun getBackgroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).backgroundColor
    override fun getTextColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).textColor

    private fun getStyle(style: ElementCategoryStyleCollection<ElementColorStyle>): ElementColorStyle {
        val categoryStyle = style.categories[getCategory()]
            ?: throw IllegalArgumentException("Category '${getCategory()}' not found.")

        return categoryStyle.specificStyles[this::class.simpleName] ?: categoryStyle.baseStyle
    }
}