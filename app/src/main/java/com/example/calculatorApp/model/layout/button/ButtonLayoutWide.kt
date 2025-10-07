package com.example.calculatorApp.model.layout.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class ButtonLayoutWide : ButtonLayoutRegular(
    weight = 2f,
    alignment = Alignment.CenterStart,
    textModifier = Modifier.fillMaxWidth(0.50f)
)