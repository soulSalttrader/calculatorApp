package com.example.calculatorApp.utils

import com.example.calculatorApp.annotations.ConceptClass
import com.example.calculatorApp.model.elements.display.DisplayCalculatorInput
import com.example.calculatorApp.ui.theme.Black
import com.example.calculatorApp.ui.theme.White

object DisplayCalculatorMappings {

    @OptIn(ConceptClass::class)
    val inputsVisualsMap: Map<DisplayCalculatorInput, VisualsDisplay> = mapOf(
        DisplayCalculatorInput.Standard to VisualsDisplay(
            background = Black,
            foreground = White,
        ),
        DisplayCalculatorInput.Scientific to VisualsDisplay(
            background = Black,
            foreground = White,
        ),
    )
}