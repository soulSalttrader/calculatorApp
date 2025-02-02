package com.example.calculatorApp.model.elements

data class ElementCategoryStyleCollectionImpl<BaseStyle : ElementColorStyle>(
    override val categories: MutableMap<ElementCategory<BaseStyle>, ElementCategoryStyle<BaseStyle>> = mutableMapOf()
) : ElementCategoryStyleCollectionBase<BaseStyle>()
