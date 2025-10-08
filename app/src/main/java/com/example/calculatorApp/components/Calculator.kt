package com.example.calculatorApp.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
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
    val isLandscape by viewModel.isLandscape.collectAsStateWithLifecycle()
    val stateUi by viewModel.stateUi.collectAsStateWithLifecycle()

    val horizontalOffset = stateUi.buttonWidth.dp


    val displayData = DisplayData(DisplayCalculatorInput.Standard)
    val rowData = ProviderRow.StandardRows.create(state, StyleCalculator.Standard)

        CalculatorPortrait(
            style = style,
            rowSpacing = rowSpacing,
            state = state,

            displayData = displayData,
            isLandscape = isLandscape,
            onAction = { action -> viewModel.onAction(action) },
            onButtonWidthCalculated = { dp -> viewModel.setButtonWidth(dp) },
            buttonWidth = horizontalOffset,

            rowData = rowData,
            shouldDraw = stateUi.shouldDraw,
            resizedTextStyle = stateUi.asTextStyle(),
            onSetInitialTextStyle = { textStyle -> viewModel.setInitialTextStyle(textStyle) },
            onAdjustTextStyle = { result, currentStyle, fallbackFontSize, fontWeight, color ->
                viewModel.adjustTextStyleIfNeeded(
                    result,
                    currentStyle,
                    fallbackFontSize,
                    fontWeight,
                    color
                )
            },
            modifier = Modifier,
            textStyle = TextStyle(
                fontSize = displayData.layout.sizeFont,
                fontWeight = displayData.layout.weightFont
            )
        )
}