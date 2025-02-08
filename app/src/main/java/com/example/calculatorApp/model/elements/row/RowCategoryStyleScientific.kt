package com.example.calculatorApp.model.elements.row

import com.example.calculatorApp.annotations.ConceptClass
import com.example.calculatorApp.model.elements.ElementCategoryStyleBase
import com.example.calculatorApp.model.elements.ElementColorStyle

@ConceptClass
class RowCategoryStyleScientific(
    override val baseStyle: ElementColorStyle,
) : ElementCategoryStyleBase<ElementColorStyle>(
    baseStyle
) {
    override val specificStyles: Map<String, ElementColorStyle> = emptyMap()
}