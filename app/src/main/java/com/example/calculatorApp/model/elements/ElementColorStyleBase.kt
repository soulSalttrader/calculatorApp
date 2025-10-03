package com.example.calculatorApp.model.elements

import androidx.compose.ui.graphics.Color

abstract class ElementColorStyleBase(
    override val backgroundColor: Color,
    override val foregroundColor: Color,
): ElementColorStyle