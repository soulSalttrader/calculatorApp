package com.example.calculatorApp.model.elements.display

import com.example.calculatorApp.annotations.ConceptMethod
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.ElementData
import com.example.calculatorApp.model.layout.ElementLayout
import com.example.calculatorApp.model.layout.display.DisplayLayoutInput

data class DisplayData(
    override val element: Display,
    override val layout: ElementLayout = DisplayLayoutInput(),
) : ElementData<ElementCategory<ElementColorStyle>, ElementCategoryStyleCollection<ElementColorStyle>, ElementLayout, ElementColorStyle> {

    fun getPlaceholderText(): String {
        return "329 329.329"
    }

    @ConceptMethod
    fun displayText(): String {
        TODO("This method is a conceptual feature under development and needs to be implemented later.")
    }
}