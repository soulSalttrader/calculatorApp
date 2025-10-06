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
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.calculatorApp.components.ComponentsUtils.formatCustom
import com.example.calculatorApp.domain.ast.Token.Companion.lastNumberOrNull
import com.example.calculatorApp.model.elements.display.DisplayCalculatorInput
import com.example.calculatorApp.model.elements.display.DisplayData
import com.example.calculatorApp.model.styles.StyleCalculator
import com.example.calculatorApp.presentation.CalculatorViewModel
import com.example.calculatorApp.ui.theme.Black

@Composable
fun CalculatorPortrait(
    rowSpacing: Dp,
    viewModel: CalculatorViewModel,
) {
    val state by viewModel.stateCal.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = rowSpacing * 5),
            verticalArrangement = Arrangement.spacedBy(rowSpacing),
        ) {

        val text = state.errorMessage.takeIf { it != null }
            ?: state.lastOperand.formatCustom().takeIf { it.isNotBlank() }
            ?: state.lastResult.takeIf { it != null }
            ?: state.expression.lastNumberOrNull()?.value.toString().formatCustom()

            CalculatorDisplayStateful(
                text = text,
                data = DisplayData(DisplayCalculatorInput.Standard),
                style = StyleCalculator.Standard,
                viewModel = viewModel
            )

            CalculatorRowStateful(viewModel)
        }
    }
}