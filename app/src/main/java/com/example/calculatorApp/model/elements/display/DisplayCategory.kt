package com.example.calculatorApp.model.elements.display

import com.example.calculatorApp.annotations.ConceptClass
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementColorStyle

sealed class DisplayCategory<BaseStyle : ElementColorStyle> : ElementCategory<BaseStyle> {

    data object Input : DisplayCategory<ElementColorStyle>()

    @ConceptClass
    data object Result : DisplayCategory<ElementColorStyle>()

    @ConceptClass
    data object Error : DisplayCategory<ElementColorStyle>()

    @ConceptClass
    data object History : DisplayCategory<ElementColorStyle>()

    @ConceptClass
    data object Memory : DisplayCategory<ElementColorStyle>()
}