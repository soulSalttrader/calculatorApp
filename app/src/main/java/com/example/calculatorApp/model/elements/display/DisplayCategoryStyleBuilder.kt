package com.example.calculatorApp.model.elements.display

import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleBuilder
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle

class DisplayCategoryStyleBuilder : ElementCategoryStyleBuilder<ElementCategory<ElementColorStyle>, ElementColorStyle>() {

    fun inputStyle(
        baseStyle: ElementColorStyle,
        scientificStyle: ElementColorStyle?,
        ) = apply {
        categories[DisplayCategory.Input] = DisplayCategoryStyleInput(
            baseStyle, scientificStyle ?: baseStyle
        )
    }

    override fun build(): ElementCategoryStyleCollection<ElementColorStyle> {
        return ElementCategoryStyleCollectionImpl(categories)
    }
}