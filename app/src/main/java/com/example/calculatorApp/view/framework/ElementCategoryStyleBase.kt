package com.example.calculatorApp.view.framework

abstract class ElementCategoryStyleBase<BaseStyle : ElementColorStyle>(
    override val baseStyle: BaseStyle,
    specificStyles: Map<String, BaseStyle>? = null,
) : ElementCategoryStyle<BaseStyle> {
    override val specificStyles: Map<String, BaseStyle> = specificStyles ?: emptyMap()
}