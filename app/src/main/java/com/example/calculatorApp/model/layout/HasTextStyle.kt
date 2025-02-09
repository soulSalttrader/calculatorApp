package com.example.calculatorApp.model.layout

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

interface HasTextStyle {
    val sizeFont: TextUnit
    val weightFont: FontWeight
}