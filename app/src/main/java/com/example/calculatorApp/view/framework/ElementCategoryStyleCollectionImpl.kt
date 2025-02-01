package com.example.calculatorApp.view.framework

data class ElementCategoryStyleCollectionImpl<BaseStyle : ElementColorStyle>(
    override val categories: MutableMap<ElementCategory<BaseStyle>, ElementCategoryStyle<BaseStyle>> = mutableMapOf()
) : ElementCategoryStyleCollectionBase<BaseStyle>()
