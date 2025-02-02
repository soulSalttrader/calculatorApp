package com.example.calculatorApp.model.elements

interface ElementCategoryStyleCollection<BaseStyle : ElementColorStyle> {
    val categories: Map<out ElementCategory<BaseStyle>, ElementCategoryStyle<BaseStyle>>
}