package com.example.calculatorApp.testData.expected

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.example.calculatorApp.model.layout.ElementLayout
import com.example.calculatorApp.model.layout.ElementLayoutPositioning
import com.example.calculatorApp.model.layout.ElementLayoutText
import com.example.calculatorApp.model.layout.HasColor

sealed interface ExpectedLayout : Expected {

    data class Button(
        override val alignment: Alignment,
        override val modifier: Modifier,
        override val shape: Shape,
        override val weight: Float,

        override val alignText: TextAlign,
        override val sizeFont: TextUnit,
        override val weightFont: FontWeight
    ) : ExpectedLayout, ElementLayout, ElementLayoutText

    data class Display(
        override val alignment: Alignment,
        override val modifier: Modifier,
        override val shape: Shape,
        override val weight: Float,

        override val alignText: TextAlign,
        override val sizeFont: TextUnit,
        override val weightFont: FontWeight
    ) : ExpectedLayout, ElementLayout, ElementLayoutText

    data class Row(
        override val alignment: Alignment,
        override val modifier: Modifier,
        override val shape: Shape,
        override val weight: Float,

        override val alignmentVertical: Alignment.Vertical,
        override val arrangementHorizontal: Arrangement.Horizontal,
        override val arrangementVertical: Arrangement.Vertical,

        override val color: Color = Color.Green,
    ) : ExpectedLayout, ElementLayout, ElementLayoutPositioning, HasColor
}



