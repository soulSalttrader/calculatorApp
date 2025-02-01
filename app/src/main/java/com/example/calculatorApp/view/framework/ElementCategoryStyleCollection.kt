package com.example.calculatorApp.view.framework

interface ElementCategoryStyleCollection<BaseStyle : ElementColorStyle> {
    val categories: Map<out ElementCategory<BaseStyle>, ElementCategoryStyle<BaseStyle>>
}