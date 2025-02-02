package com.example.calculatorApp.model.elements.button

import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementColorStyle

sealed class ButtonCategory<BaseStyle : ElementColorStyle> : ElementCategory<BaseStyle> {

    data object Arithmetic : ButtonCategory<ElementColorStyle>()
    data object Control : ButtonCategory<ElementColorStyle>()
    data object Number : ButtonCategory<ElementColorStyle>()
}