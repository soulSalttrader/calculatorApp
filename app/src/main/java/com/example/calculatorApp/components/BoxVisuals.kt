package com.example.calculatorApp.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.layout.ElementLayout

data class BoxVisuals(
    val modifier: Modifier = Modifier,
    val layout: ElementLayout,
    val backgroundColor: Color,
    val foregroundColor: Color,
)