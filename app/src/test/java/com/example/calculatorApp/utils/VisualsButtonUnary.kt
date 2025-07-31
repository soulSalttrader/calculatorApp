package com.example.calculatorApp.utils

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.symbols.SymbolButton

data class VisualsButtonUnary(
    override val background: Color,
    override val foreground: Color,
    val symbol: SymbolButton,
    val isPrefix: Boolean,
) : Visuals