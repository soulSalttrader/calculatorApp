package com.example.calculatorApp.model.layout.display

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorApp.utils.Constants.SIZE_FONT_DEFAULT
import com.example.calculatorApp.utils.PrettyPrinter.prettyString

class DisplayLayoutInput(
    override val alignment: Alignment = Alignment.BottomEnd,
    override val modifier: Modifier = Modifier.fillMaxWidth().padding(4.dp),
    override val shape: Shape = RectangleShape,
    override val weight: Float = 2f,

    override val alignText: TextAlign = TextAlign.End,
    override val textModifier: Modifier = Modifier,
    override val sizeFont: TextUnit = SIZE_FONT_DEFAULT.sp,
    override val weightFont: FontWeight = FontWeight.Light,
) : DisplayLayout {
    override fun toString(): String = prettyString()
}