package com.example.calculatorApp.testData.input

import com.example.calculatorApp.model.elements.Element
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle

typealias UIElement = Element<
        ElementCategory<ElementColorStyle>,
        ElementCategoryStyleCollection<ElementColorStyle>,
        ElementColorStyle
    >

sealed interface InputElement : Input {
    val element: UIElement

    data class Button(
        override val element: UIElement
    ) : InputElement

    data class Display(
        override val element: UIElement
    ) : InputElement

    data class Row(
        override val element: UIElement
    ) : InputElement
}