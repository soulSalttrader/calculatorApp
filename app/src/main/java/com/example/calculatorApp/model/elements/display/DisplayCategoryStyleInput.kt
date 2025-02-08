package com.example.calculatorApp.model.elements.display

import com.example.calculatorApp.annotations.ConceptClass
import com.example.calculatorApp.model.elements.ElementCategoryStyleBase
import com.example.calculatorApp.model.elements.ElementColorStyle

class DisplayCategoryStyleInput(
    override val baseStyle: ElementColorStyle,
    scientificStyle: ElementColorStyle? = null,
) : ElementCategoryStyleBase<ElementColorStyle>(
    baseStyle,
    run {
        @OptIn(ConceptClass::class)
        val scientificClassName = DisplayCalculatorInput.Scientific::class.simpleName.takeIf { it != null } ?: "DefaultScientific"
        mapOf(scientificClassName to (scientificStyle ?: baseStyle))
    }
)