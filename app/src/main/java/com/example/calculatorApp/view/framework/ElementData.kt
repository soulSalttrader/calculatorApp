package com.example.calculatorApp.view.framework

interface ElementData<
        Category : ElementCategory<BaseStyle>,
        Style : ElementCategoryStyleCollection<BaseStyle>,
        Layout : ElementLayout,
        BaseStyle : ElementColorStyle,
> {
    val element: Element<Category, Style, BaseStyle>
    val layout: Layout
}