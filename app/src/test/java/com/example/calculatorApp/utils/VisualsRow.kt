package com.example.calculatorApp.utils

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.button.ButtonData

data class VisualsRow(
    override val background: Color,
    override val foreground: Color,
    val buttonData: Sequence<ButtonData>,
) : Visuals
