package com.example.calculatorApp.model.layout.row

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.calculatorApp.model.layout.ElementLayout
import com.example.calculatorApp.model.layout.ElementLayoutPositioning
import com.example.calculatorApp.model.layout.HasColor
import com.example.calculatorApp.ui.theme.Black
import com.example.calculatorApp.utils.PrettyPrinter.prettyString

class RowLayoutStandard(
    override val alignment: Alignment = Alignment.Center,
    override val modifier: Modifier = Modifier,
    override val shape: Shape = RectangleShape,
    override val weight: Float = 1f,

    override val alignmentVertical: Alignment.Vertical = Alignment.CenterVertically,
    override val arrangementHorizontal: Arrangement.Horizontal = Arrangement.spacedBy(8.dp),
    override val arrangementVertical: Arrangement.Vertical = Arrangement.spacedBy(8.dp),

    override val color: Color = Black,
) : ElementLayout, ElementLayoutPositioning, HasColor {

    val rowStandardModifier = Modifier
        .clip(shape)
        .padding(horizontal = 16.dp)
        .fillMaxWidth()
        .background(color)

    override fun toString(): String = prettyString()
}