package com.example.calculatorApp.view.framework

abstract class ElementCategoryStyleCollectionBase<BaseStyle : ElementColorStyle> :
    ElementCategoryStyleCollection<BaseStyle> {
    override val categories: MutableMap<out ElementCategory<BaseStyle>, ElementCategoryStyle<BaseStyle>> = mutableMapOf()
}