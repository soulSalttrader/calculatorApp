package com.example.calculatorApp.model.styles

import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle

interface Style {
    val buttonStyle: ElementCategoryStyleCollection<ElementColorStyle>
    val displayStyle: ElementCategoryStyleCollection<ElementColorStyle>
    val rowStyle: ElementCategoryStyleCollection<ElementColorStyle>
}