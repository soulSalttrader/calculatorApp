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
import com.example.calculatorApp.model.layout.ElementLayout

class DisplayLayoutInput(
    override val alignment: Alignment = Alignment.BottomEnd,
    override val textAlign: TextAlign = TextAlign.End,
    override val modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp),
    override val weight: Float = 2f,
    override val fontSize: TextUnit = 85.sp,
    override val fontWeight: FontWeight = FontWeight.Light,
    override val shape: Shape = RectangleShape,
) : ElementLayout