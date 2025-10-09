package com.example.calculatorApp.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.example.calculatorApp.model.elements.display.DisplayData
import com.example.calculatorApp.model.styles.Style


data class ParamsDisplayText(
    val modifier: Modifier = Modifier,
    val data: DisplayData,
    val style: Style,
    val text: String,
    val textStyle: TextStyle,
    val shouldDraw: Boolean,
    val resizedTextStyle: TextStyle
)