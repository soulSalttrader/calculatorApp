package com.example.calculatorApp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.calculatorApp.components.ComponentsUtils.formatCustom
import com.example.calculatorApp.domain.action.CalculatorAction
import com.example.calculatorApp.domain.ast.Token.Companion.lastNumberOrNull
import com.example.calculatorApp.model.elements.display.DisplayData
import com.example.calculatorApp.model.elements.row.RowData
import com.example.calculatorApp.model.state.CalculatorStateDomain
import com.example.calculatorApp.model.styles.Style
import com.example.calculatorApp.presentation.CalculatorViewModel
import com.example.calculatorApp.ui.theme.Black

@Composable
fun CalculatorPortraitStateful(
    displayData: DisplayData,
    rowData: Sequence<RowData>,
    style: Style,
    rowSpacing: Dp,
    viewModel: CalculatorViewModel,
) {
    val state by viewModel.stateCal.collectAsStateWithLifecycle()

    val text = state.errorMessage.takeIf { it != null }
        ?: state.lastOperand.formatCustom().takeIf { it.isNotBlank() }
        ?: state.lastResult.takeIf { it != null }
        ?: state.expression.lastNumberOrNull()?.value.toString().formatCustom()

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

            CalculatorDisplayStateful(
                text = text,
                data = displayData,
                style = style,
                viewModel = viewModel
            )

            CalculatorRowStateful(
                data = rowData,
                style = style,
                viewModel = viewModel,
            )
        }
    }
}

@Composable
fun CalculatorPortrait(
    modifier: Modifier,
    displayData: DisplayData,
    rowData: Sequence<RowData>,
    style: Style,
    textStyle: TextStyle,
    rowSpacing: Dp,
    state: CalculatorStateDomain,
    buttonWidth: Dp = 0.dp,
    shouldDraw: Boolean = true,
    resizedTextStyle: TextStyle = TextStyle.Default,
    onSetInitialTextStyle: (TextStyle) -> Unit,
    onAdjustTextStyle: (TextLayoutResult, TextStyle, TextUnit, FontWeight, Color) -> Unit = { _, _, _, _, _ -> },
    isLandscape: Boolean,
    onAction: (CalculatorAction) -> Unit,
    onButtonWidthCalculated: (Dp) -> Unit,
) {

    val text = state.errorMessage.takeIf { it != null }
        ?: state.lastOperand.formatCustom().takeIf { it.isNotBlank() }
        ?: state.lastResult.takeIf { it != null }
        ?: state.expression.lastNumberOrNull()?.value.toString().formatCustom()

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

            CalculatorDisplay(
                text = text,
                data = displayData,
                style = style,
                onSetInitialTextStyle = onSetInitialTextStyle,
                onAdjustTextStyle = onAdjustTextStyle,
                modifier = modifier,
                buttonWidth = buttonWidth,
                textStyle = textStyle,
                shouldDraw = shouldDraw,
                resizedTextStyle = resizedTextStyle,
            )

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