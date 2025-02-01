package com.example.calculatorApp.view.framework

interface ElementCategoryStyle<BaseStyle : ElementColorStyle> {
    val baseStyle: BaseStyle
    val specificStyles: Map<String, BaseStyle>
}