package com.example.calculatorApp.view.framework

import androidx.compose.ui.graphics.Color

interface Element<
        Category : com.example.calculatorApp.view.framework.ElementCategory<BaseStyle>,
        Style : ElementCategoryStyleCollection<BaseStyle>,
        BaseStyle : ElementColorStyle,
        > {

    fun getBackgroundColor(style: Style): Color
    fun getTextColor(style: Style): Color
    fun getCategory(): Category
}