package com.example.calculatorApp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculatorApp.R
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
import com.example.calculatorApp.model.styles.StyleCalculator

@Composable
fun CalculatorRow(paramsRow: ParamsRow) {
    val density = LocalDensity.current

    paramsRow.data.forEach { rowData ->
        val rowLayout = (rowData.layout as? RowLayoutStandard) ?: RowLayoutStandard()

        Column(
            verticalArrangement = rowLayout.arrangementVertical
        ) {
            Row(
                modifier = rowLayout.rowStandardModifier,
                horizontalArrangement = rowLayout.arrangementHorizontal,
                verticalAlignment = rowLayout.alignmentVertical
            ) {
                rowData.element.buttons.forEach { buttonData ->
                    val shouldCalculateCenter = buttonData.element == ButtonCalculatorBinary.Division

                    val buttonModifier = Modifier
                        .weight(buttonData.layout.weight)
                        .aspectRatio(buttonData.layout.weight)
                        .aspectRationOriented(paramsRow.isLandscape, buttonData.layout.weight)
                        .calculateButtonCenter(
                            shouldCalculateCenter = shouldCalculateCenter,
                            density = density,
                            onCenterCalculated = paramsRow.uiCallbacks.onButtonWidthCalculated
                        )
                        .clickable {
                            paramsRow.uiCallbacks.onAction(
                                CalculatorAction.ButtonPressed(buttonData.element)
                            )
                        }

                    val shouldHighlight = buttonData.element.shouldHighlight(paramsRow.state)
                    val elementStyle = buttonData.element.getStyle(paramsRow.style.buttonStyle)

                    val buttonColor = if (shouldHighlight) colorResource(R.color.button_binary_background_highlight) else elementStyle.backgroundColor
                    val textColor = if (shouldHighlight) colorResource(R.color.button_binary_foreground_highlight) else elementStyle.foregroundColor

                    val paramsButton = ParamsButton(
                        data = buttonData,
                        visuals = BoxVisuals(
                            modifier = buttonModifier,
                            layout = buttonData.layout,
                            backgroundColor = buttonColor,
                            foregroundColor = textColor
                        ),
                        semantics = BoxSemantics(
                            role = Role.Button,
                            contentDescription = buttonData.element.symbol.label
                        )
                    )

                    CalculatorButton(paramsButton)
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun CalculatorRowPreview() {
    val data = sequenceOf(
        RowData(
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

    val state = CalculatorStateDomain()

    val callbacksUi = CalculatorUiCallbacks(
        onAction = { action -> /* No-op */ },
        onButtonWidthCalculated = { dp -> /* No-op */ },
    )

    val paramsRow = ParamsRow(
        data = data,
        style = StyleCalculator.Standard,
        isLandscape = false,
        state = state,
        uiCallbacks = callbacksUi
    )

    CalculatorRow(paramsRow = paramsRow)
}