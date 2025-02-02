package com.example.calculatorApp.model.elements

abstract class ElementCategoryStyleCollectionBase<BaseStyle : ElementColorStyle> :
    ElementCategoryStyleCollection<BaseStyle> {
    override val categories: MutableMap<out ElementCategory<BaseStyle>, ElementCategoryStyle<BaseStyle>> = mutableMapOf()
}