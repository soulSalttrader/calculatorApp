package com.example.calculatorApp.components

import com.example.calculatorApp.model.elements.button.ButtonData

data class ParamsButton(
    val data: ButtonData,
    val visuals: BoxVisuals,
    val semantics: BoxSemantics,
)