package com.example.calculatorApp.testData.expected

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

sealed interface ExpectedLayout : Expected {
    val alignment: Alignment
    val modifier: Modifier
    val shape: Shape
    val weight: Float

    val alignText: TextAlign
    val sizeFont: TextUnit
    val weightFont: FontWeight

    data class Button(
        override val alignment: Alignment,
        override val modifier: Modifier,
        override val shape: Shape,
        override val weight: Float,

        override val alignText: TextAlign,
        override val sizeFont: TextUnit,
        override val weightFont: FontWeight
    ) : ExpectedLayout
}



