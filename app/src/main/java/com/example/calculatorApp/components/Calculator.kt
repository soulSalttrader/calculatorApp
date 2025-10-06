package com.example.calculatorApp.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.calculatorApp.presentation.CalculatorViewModel

@Composable
fun Calculator(
    rowSpacing: Dp = 6.dp,
    viewModel: CalculatorViewModel,
) {

    CalculatorPortrait(
        viewModel = viewModel,
        rowSpacing = rowSpacing,
    )
}