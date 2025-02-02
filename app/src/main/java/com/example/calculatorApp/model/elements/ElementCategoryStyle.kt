package com.example.calculatorApp.model.elements

interface ElementCategoryStyle<BaseStyle : ElementColorStyle> {
    val baseStyle: BaseStyle
    val specificStyles: Map<String, BaseStyle>
}