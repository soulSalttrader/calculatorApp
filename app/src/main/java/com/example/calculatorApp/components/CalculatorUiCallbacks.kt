package com.example.calculatorApp.components

import androidx.compose.ui.unit.Dp
import com.example.calculatorApp.domain.action.CalculatorAction

data class CalculatorUiCallbacks(
    val onAction: (CalculatorAction) -> Unit,
    val onButtonWidthCalculated: (Dp) -> Unit,
)