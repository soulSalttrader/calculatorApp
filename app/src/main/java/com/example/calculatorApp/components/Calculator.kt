package com.example.calculatorApp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.calculatorApp.components.ComponentsUtils.formatCustom
import com.example.calculatorApp.domain.ast.Token.Companion.lastNumberOrNull
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

    val buttonWidthDp = stateUi.buttonWidth.dp
    val displayData = DisplayData(DisplayCalculatorInput.Standard)

    val text = state.errorMessage.takeIf { it != null }
        ?: state.lastOperand.formatCustom().takeIf { it.isNotBlank() }
        ?: state.lastResult.takeIf { it != null }
        ?: state.expression.lastNumberOrNull()?.value.toString().formatCustom()

    val paramsDisplayText = ParamsDisplayText(
        modifier = Modifier.padding(horizontal = buttonWidthDp / 1.5f),
        data = displayData,
        style = style,
        text = text,
        textStyle = TextStyle(
            fontSize = displayData.layout.sizeFont,
            fontWeight = displayData.layout.weightFont
        ),
        shouldDraw = stateUi.shouldDraw,
        resizedTextStyle = stateUi.asTextStyle(),
    )

    val paramsTextStyle = ParamsTextStyle(
        onSetInitialTextStyle = { textStyle -> viewModel.setInitialTextStyle(textStyle) },
        onAdjustTextStyle = { result, currentStyle, fallbackFontSize, fontWeight, color ->
                viewModel.adjustTextStyleIfNeeded(
                    result,
                    currentStyle,
                    fallbackFontSize,
                    fontWeight,
                    color
                )
        }
    )

    val paramsStyledText = ParamsStyledText(
        paramsDisplayText = paramsDisplayText,
        paramsTextStyle = paramsTextStyle,
    )

    val rowData = ProviderRow.StandardRows.create(state, StyleCalculator.Standard)

        CalculatorPortrait(
            paramsStyledText = paramsStyledText,

            state = state,
            style = style,
            rowData = rowData,
            rowSpacing = rowSpacing,

            isLandscape = isLandscape,
            onAction = { action -> viewModel.onAction(action) },
            onButtonWidthCalculated = { dp -> viewModel.setButtonWidth(dp) },
        )
}