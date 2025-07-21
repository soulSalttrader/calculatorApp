package com.example.calculatorApp.model.styles

import com.example.calculatorApp.model.elements.ElementColorStyleImpl
import com.example.calculatorApp.model.elements.display.DisplayCategoryStyleBuilder
import com.example.calculatorApp.ui.theme.Black
import com.example.calculatorApp.ui.theme.White

object StylesDisplay {

    val iDisplayStyleInput = DisplayCategoryStyleBuilder()
        .inputStyle(
            baseStyle = ElementColorStyleImpl(backgroundColor = Black, foregroundColor = White),
            scientificStyle = ElementColorStyleImpl(backgroundColor = Black, foregroundColor = White),
        )
        .build()
}