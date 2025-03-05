package com.example.calculatorApp.model.elements.button

import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleBuilder
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle

class ButtonCategoryStyleBuilder : ElementCategoryStyleBuilder<ElementCategory<ElementColorStyle>, ElementColorStyle>() {

    fun binaryStyle(baseStyle: ElementColorStyle) = apply {
        categories[ButtonCategory.Binary] = ButtonCategoryStyleBinary(baseStyle)
    }

    fun unaryStyle(baseStyle: ElementColorStyle) = apply {
        categories[ButtonCategory.Unary] = ButtonCategoryStyleUnary(baseStyle)
    }

    fun controlStyle(
        baseStyle: ElementColorStyle,
        decimalStyle: ElementColorStyle? = null,
        equalsStyle: ElementColorStyle? = null,
    ) = apply {
        categories[ButtonCategory.Control] = ButtonCategoryStyleControl(
            baseStyle, decimalStyle ?: baseStyle, equalsStyle ?: baseStyle
        )
    }

    fun numberStyle(baseStyle: ElementColorStyle) = apply {
        categories[ButtonCategory.Number] = ButtonCategoryStyleNumber(baseStyle)
    }

    fun parenthesisStyle(baseStyle: ElementColorStyle) = apply {
        categories[ButtonCategory.Parenthesis] = ButtonCategoryStyleParenthesis(baseStyle)
    }

    override fun build(): ElementCategoryStyleCollection<ElementColorStyle> {
        return ElementCategoryStyleCollectionImpl(categories)
    }
}