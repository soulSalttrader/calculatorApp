package com.example.calculatorApp.model.elements.button

import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.ElementData
import com.example.calculatorApp.model.layout.ElementLayout
import com.example.calculatorApp.model.layout.button.ButtonLayout
import com.example.calculatorApp.model.layout.button.ButtonLayoutRegular

data class ButtonData(
    override val element: Button,
    override val layout: ButtonLayout = ButtonLayoutRegular(),
) : ElementData<ElementCategory<ElementColorStyle>, ElementCategoryStyleCollection<ElementColorStyle>, ElementLayout, ElementColorStyle>