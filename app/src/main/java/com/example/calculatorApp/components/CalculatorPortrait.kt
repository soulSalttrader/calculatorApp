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
import com.example.calculatorApp.ui.theme.Black

@Composable
fun CalculatorPortrait(paramsPortrait: ParamsPortrait) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Column(
            modifier = Modifier
                .background(Black)
                .align(Alignment.BottomCenter)
                .padding(bottom = paramsPortrait.rowSpacing * 8),
            verticalArrangement = Arrangement.spacedBy(paramsPortrait.rowSpacing),
        ) {

            CalculatorDisplay(paramsStyledBox = paramsPortrait.styledBox)
            CalculatorRow(paramsRow = paramsPortrait.row)
        }
    }
}