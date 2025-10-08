package com.example.calculatorApp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.calculatorApp.components.ComponentsUtils.aspectRationOriented
import com.example.calculatorApp.components.ComponentsUtils.calculateButtonCenter
import com.example.calculatorApp.domain.action.CalculatorAction
import com.example.calculatorApp.model.ProviderRowConfigs.buttonSequence
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.elements.row.RowCalculatorStandard
import com.example.calculatorApp.model.elements.row.RowData
import com.example.calculatorApp.model.layout.row.RowLayoutStandard
import com.example.calculatorApp.model.state.CalculatorStateDomain
import com.example.calculatorApp.model.styles.Style
import com.example.calculatorApp.model.styles.StyleCalculator
import com.example.calculatorApp.presentation.CalculatorViewModel

@Composable
fun CalculatorRowStateful(
    data: Sequence<RowData>,
    style: Style,
    viewModel: CalculatorViewModel,
) {

    val state by viewModel.stateCal.collectAsStateWithLifecycle()
    val isLandscape by viewModel.isLandscape.collectAsStateWithLifecycle()

    CalculatorRow(
        data = data,
        style = style,
        state = state,
        isLandscape = isLandscape,
        onAction = { action -> viewModel.onAction(action) },
        onButtonWidthCalculated = { dp -> viewModel.setButtonWidth(dp) },
    )
}

@Composable
fun CalculatorRow(
    data: Sequence<RowData>,
    style: Style,
    state: CalculatorStateDomain,
    isLandscape: Boolean,
    onAction: (CalculatorAction) -> Unit,
    onButtonWidthCalculated: (Dp) -> Unit,
) {
    val density = LocalDensity.current

    data.forEach { rowData ->
        val rowLayout = (rowData.layout as? RowLayoutStandard) ?: RowLayoutStandard()

        Column(
            verticalArrangement = rowLayout.arrangementVertical
        ) {
            Row(
                modifier = rowLayout.rowStandardModifier,
                horizontalArrangement = rowLayout.arrangementHorizontal,
                verticalAlignment = rowLayout.alignmentVertical
            ) {
                rowData.element.buttons.forEach { button ->
                    val shouldCalculateCenter = button.element == ButtonCalculatorBinary.Division

                    CalculatorButton(
                        data = button,
                        style = style,
                        state = state,
                        modifier = Modifier
                            .weight(button.layout.weight)
                            .aspectRatio(button.layout.weight)
                            .aspectRationOriented(isLandscape, button.layout.weight)
                            .calculateButtonCenter(
                                shouldCalculateCenter = shouldCalculateCenter,
                                density = density,
                                onCenterCalculated = onButtonWidthCalculated
                            )
                            .clickable { onAction(CalculatorAction.ButtonPressed(button.element)) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun CalculatorRowPreview() {
    val mockRows = sequenceOf( RowData(
            RowCalculatorStandard.Standard1(
                buttonSequence(
                    ButtonCalculatorControl.AllClear,
                    ButtonCalculatorUnary.Sign,
                    ButtonCalculatorUnary.Percentage,
                    ButtonCalculatorBinary.Division
                )
            )
        )
    )

    CalculatorRow(
        data = mockRows,
        style = StyleCalculator.Standard,
        state = CalculatorStateDomain(),
        isLandscape = false, // Preview in portrait
        onAction = { action -> /* Log or ignore for preview */ },
        onButtonWidthCalculated = { dp -> /* Log or ignore for preview */ },
    )
}