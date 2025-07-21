package com.example.calculatorApp.model.styles

import com.example.calculatorApp.model.elements.ElementColorStyleImpl
import com.example.calculatorApp.model.elements.button.ButtonCategoryStyleBuilder
import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge
import com.example.calculatorApp.ui.theme.White

object StylesButton {

    val iButtonStyle = ButtonCategoryStyleBuilder()
        .binaryStyle(
            baseStyle = ElementColorStyleImpl(backgroundColor = VividGamboge, foregroundColor = White),
        )
        .unaryStyle(
            baseStyle = ElementColorStyleImpl(backgroundColor = SilverGrey, foregroundColor = Onyx),
        )
        .controlStyle(
            baseStyle = ElementColorStyleImpl(backgroundColor = SilverGrey, foregroundColor = Onyx),
            decimalStyle = ElementColorStyleImpl(backgroundColor = Onyx, foregroundColor = White),
            equalsStyle = ElementColorStyleImpl(backgroundColor = VividGamboge, foregroundColor = White),
        )
        .numberStyle(
            baseStyle = ElementColorStyleImpl(backgroundColor = Onyx, foregroundColor = White),
        )
        .parenthesisStyle(
            baseStyle = ElementColorStyleImpl(backgroundColor = Onyx, foregroundColor = White),
        )
        .build()
}