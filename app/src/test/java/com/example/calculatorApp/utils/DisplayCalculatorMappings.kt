package com.example.calculatorApp.utils

import com.example.calculatorApp.annotations.ConceptClass
import com.example.calculatorApp.model.elements.display.DisplayCalculatorInput
import com.example.calculatorApp.ui.theme.Black

object DisplayCalculatorMappings {

    val inputsColorMap: Map<DisplayCalculatorInput, Any> = buildMap {
        put(DisplayCalculatorInput.Standard, Black)
        put(@OptIn(ConceptClass::class) DisplayCalculatorInput.Scientific, Black)
    }
}