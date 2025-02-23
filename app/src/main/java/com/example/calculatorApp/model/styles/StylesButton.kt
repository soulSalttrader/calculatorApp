package com.example.calculatorApp.model.styles

import com.example.calculatorApp.model.elements.ElementColorStyleImpl
import com.example.calculatorApp.model.elements.button.ButtonCategoryStyleBuilder
import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge
import com.example.calculatorApp.ui.theme.White

object StylesButton {

    val iButtonStyle = ButtonCategoryStyleBuilder()
        .arithmeticStyle(
            baseStyle = ElementColorStyleImpl(backgroundColor = VividGamboge, textColor = White),
        )
        .unaryStyle(
            baseStyle = ElementColorStyleImpl(backgroundColor = SilverGrey, textColor = Onyx),
        )
        .controlStyle(
            baseStyle = ElementColorStyleImpl(backgroundColor = SilverGrey, textColor = Onyx),
            decimalStyle = ElementColorStyleImpl(backgroundColor = Onyx, textColor = White),
            equalsStyle = ElementColorStyleImpl(backgroundColor = VividGamboge, textColor = White),
        )
        .numberStyle(
            baseStyle = ElementColorStyleImpl(backgroundColor = Onyx, textColor = White),
        )
        .parenthesisStyle(
            baseStyle = ElementColorStyleImpl(backgroundColor = Onyx, textColor = White),
        )
        .build()
}