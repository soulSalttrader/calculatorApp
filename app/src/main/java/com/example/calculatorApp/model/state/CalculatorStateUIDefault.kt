package com.example.calculatorApp.model.state

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.calculatorApp.utils.Constants.SIZE_FONT_DEFAULT

object CalculatorStateUIDefault {

    val defaultStyle = TextStyle(
        fontSize = SIZE_FONT_DEFAULT.sp,
        fontWeight = FontWeight.Light,
        color = Color.White
    )
}