package com.example.calculatorApp.model.elements

import androidx.compose.ui.graphics.Color

interface Element<
        Category : ElementCategory<BaseStyle>,
        Style : ElementCategoryStyleCollection<BaseStyle>,
        BaseStyle : ElementColorStyle,
        > {

    fun getBackgroundColor(style: Style): Color
    fun getForegroundColor(style: Style): Color
    fun getCategory(): Category
}