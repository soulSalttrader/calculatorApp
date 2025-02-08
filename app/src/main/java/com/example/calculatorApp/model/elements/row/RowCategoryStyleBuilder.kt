package com.example.calculatorApp.model.elements.row

import com.example.calculatorApp.annotations.ConceptClass
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleBuilder
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle

class RowCategoryStyleBuilder : ElementCategoryStyleBuilder<ElementCategory<ElementColorStyle>, ElementColorStyle>() {

    fun standardStyle(
        baseStyle: ElementColorStyle
    ) = apply {
        categories[RowCategory.Standard] = RowCategoryStyleStandard(baseStyle)
    }

    @OptIn(ConceptClass::class)
    fun scientificStyle(
        baseStyle: ElementColorStyle
    ) = apply {
        categories[RowCategory.Scientific] = RowCategoryStyleScientific(baseStyle)
    }

    override fun build(): ElementCategoryStyleCollection<ElementColorStyle> {
        return ElementCategoryStyleCollectionImpl(categories)
    }
}