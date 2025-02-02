package com.example.calculatorApp.model.elements.button

import com.example.calculatorApp.model.elements.ElementCategoryStyleBase
import com.example.calculatorApp.model.elements.ElementColorStyle

data class ButtonCategoryStyleNumber(
    override val baseStyle: ElementColorStyle,
) : ElementCategoryStyleBase<ElementColorStyle>(baseStyle) {
    override val specificStyles: Map<String, ElementColorStyle> = emptyMap()
}