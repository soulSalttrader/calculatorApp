package com.example.calculatorApp.model.elements.row

import com.example.calculatorApp.model.elements.Element
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.button.ButtonData

interface Row : Element<ElementCategory<ElementColorStyle>, ElementCategoryStyleCollection<ElementColorStyle>, ElementColorStyle> {

    val buttons: List<ButtonData>
}