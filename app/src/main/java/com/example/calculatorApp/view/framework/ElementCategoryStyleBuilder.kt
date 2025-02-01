package com.example.calculatorApp.view.framework

abstract class ElementCategoryStyleBuilder<Category : ElementCategory<BaseStyle>, BaseStyle : ElementColorStyle>(
    protected val categories: MutableMap<Category, ElementCategoryStyle<BaseStyle>> = mutableMapOf()
) {

    abstract fun build(): ElementCategoryStyleCollection<BaseStyle>
}