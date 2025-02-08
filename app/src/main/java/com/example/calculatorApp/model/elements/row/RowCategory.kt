package com.example.calculatorApp.model.elements.row

import com.example.calculatorApp.annotations.ConceptClass
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementColorStyle

sealed class RowCategory<BaseStyle : ElementColorStyle> : ElementCategory<BaseStyle> {

    data object Standard : RowCategory<ElementColorStyle>()

    @ConceptClass
    data object Scientific : RowCategory<ElementColorStyle>()
}