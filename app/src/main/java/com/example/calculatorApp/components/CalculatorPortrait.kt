package com.example.calculatorApp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.calculatorApp.domain.action.CalculatorAction
import com.example.calculatorApp.model.elements.row.RowData
import com.example.calculatorApp.model.state.CalculatorStateDomain
import com.example.calculatorApp.model.styles.Style
import com.example.calculatorApp.ui.theme.Black

@Composable
fun CalculatorPortrait(
    paramsStyledText: ParamsStyledText,
    rowData: Sequence<RowData>,
    style: Style,
    rowSpacing: Dp,
    state: CalculatorStateDomain,
    isLandscape: Boolean,
    onAction: (CalculatorAction) -> Unit,
    onButtonWidthCalculated: (Dp) -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = rowSpacing * 8),
            verticalArrangement = Arrangement.spacedBy(rowSpacing),
        ) {

            CalculatorDisplay(paramsStyledText = paramsStyledText)

            CalculatorRow(
                data = rowData,
                style = style,
                isLandscape = isLandscape,
                onAction = onAction,
                onButtonWidthCalculated = onButtonWidthCalculated,
                state = state
            )
        }
    }
}