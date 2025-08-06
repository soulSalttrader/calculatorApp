package com.example.calculatorApp.testData.expected

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.button.ButtonData

data class ExpectedRow(
    override val background: Color,
    override val foreground: Color,
    val buttonData: Sequence<ButtonData>,
) : ExpectedElement
