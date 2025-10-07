package com.example.calculatorApp.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.calculatorApp.model.ProviderRow
import com.example.calculatorApp.model.elements.display.DisplayCalculatorInput
import com.example.calculatorApp.model.elements.display.DisplayData
import com.example.calculatorApp.model.styles.Style
import com.example.calculatorApp.model.styles.StyleCalculator
import com.example.calculatorApp.presentation.CalculatorViewModel

@Composable
fun Calculator(
    style: Style = StyleCalculator.Standard,
    rowSpacing: Dp = 6.dp,
    viewModel: CalculatorViewModel,
) {
    val state by viewModel.stateCal.collectAsStateWithLifecycle()

    CalculatorPortraitStateful(
        displayData = DisplayData(DisplayCalculatorInput.Standard),
        rowData = ProviderRow.StandardRows.create(state, StyleCalculator.Standard),
        style = style,
        rowSpacing = rowSpacing,
        viewModel = viewModel,
    )
}