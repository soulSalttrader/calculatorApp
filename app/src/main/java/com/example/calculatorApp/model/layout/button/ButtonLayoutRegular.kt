package com.example.calculatorApp.model.layout.button

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.calculatorApp.utils.PrettyPrinter.prettyString

open class ButtonLayoutRegular(
    override val alignment: Alignment = Alignment.Center,
    override val modifier: Modifier = Modifier,
    override val shape: Shape = CircleShape,
    override val weight: Float = 1f,

    override val alignText: TextAlign = TextAlign.Center,
    override val textModifier: Modifier = Modifier,
    override val sizeFont: TextUnit = 42.sp,
    override val weightFont: FontWeight = FontWeight.Normal,
) : ButtonLayout {
    override fun toString(): String = prettyString()
}