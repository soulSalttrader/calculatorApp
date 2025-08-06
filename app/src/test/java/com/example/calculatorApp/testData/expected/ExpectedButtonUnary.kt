package com.example.calculatorApp.testData.expected

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.symbols.SymbolButton

data class ExpectedButtonUnary(
    override val background: Color,
    override val foreground: Color,
    val symbol: SymbolButton,
    val isPrefix: Boolean,
) : ExpectedElement