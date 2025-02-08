package com.example.calculatorApp.model.styles

import com.example.calculatorApp.model.elements.ElementColorStyleImpl
import com.example.calculatorApp.model.elements.row.RowCategoryStyleBuilder
import com.example.calculatorApp.ui.theme.Black
import com.example.calculatorApp.ui.theme.White

object StylesRow {

    val iRowStyle = RowCategoryStyleBuilder()
        .standardStyle(
            baseStyle = ElementColorStyleImpl(backgroundColor = Black, textColor = White),
        )
        .scientificStyle(
            baseStyle = ElementColorStyleImpl(backgroundColor = Black, textColor = White),
        ).build()
}