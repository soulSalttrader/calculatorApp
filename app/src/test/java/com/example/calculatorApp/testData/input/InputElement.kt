package com.example.calculatorApp.testData.input

import com.example.calculatorApp.model.elements.Element
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle

sealed class InputElement : Input {

    data class Button(
        val element: Element
        <
            ElementCategory<ElementColorStyle>,
            ElementCategoryStyleCollection<ElementColorStyle>,
            ElementColorStyle
        >
    ) : InputElement()
}
