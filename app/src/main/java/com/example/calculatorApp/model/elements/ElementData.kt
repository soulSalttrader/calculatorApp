package com.example.calculatorApp.model.elements

import com.example.calculatorApp.model.layout.ElementLayout

interface ElementData<
        Category : ElementCategory<BaseStyle>,
        Style : ElementCategoryStyleCollection<BaseStyle>,
        Layout : ElementLayout,
        BaseStyle : ElementColorStyle,
> {
    val element: Element<Category, Style, BaseStyle>
    val layout: Layout
}