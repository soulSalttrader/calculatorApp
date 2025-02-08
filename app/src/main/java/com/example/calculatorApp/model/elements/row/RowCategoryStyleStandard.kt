package com.example.calculatorApp.model.elements.row

import com.example.calculatorApp.model.elements.ElementCategoryStyleBase
import com.example.calculatorApp.model.elements.ElementColorStyle

class RowCategoryStyleStandard(
    override val baseStyle: ElementColorStyle,
) : ElementCategoryStyleBase<ElementColorStyle>(
    baseStyle
) {
    override val specificStyles: Map<String, ElementColorStyle> = emptyMap()
}