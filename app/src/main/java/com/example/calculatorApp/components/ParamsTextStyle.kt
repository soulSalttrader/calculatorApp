package com.example.calculatorApp.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

data class ParamsTextStyle(
    val onSetInitialTextStyle: (TextStyle) -> Unit,
    val onAdjustTextStyle: (TextLayoutResult, TextStyle, TextUnit, FontWeight, Color) -> Unit
)