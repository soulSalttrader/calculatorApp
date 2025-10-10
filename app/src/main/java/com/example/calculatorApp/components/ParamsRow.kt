package com.example.calculatorApp.components

import com.example.calculatorApp.model.elements.row.RowData
import com.example.calculatorApp.model.state.CalculatorStateDomain
import com.example.calculatorApp.model.styles.Style

data class ParamsRow(
    val data: Sequence<RowData>,
    val style: Style,
    val state: CalculatorStateDomain,
    val isLandscape: Boolean,
    val uiCallbacks: CalculatorUiCallbacks,
)