package com.example.calculatorApp.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.isUnspecified

object ComponentsUtils {

    fun Modifier.aspectRationOriented(
        isLandscape: Boolean,
        weight: Float
    ) = isLandscape.takeIf { it }?.let { this } ?: this.aspectRatio(weight)

    fun Modifier.calculateButtonCenter(
        shouldCalculateCenter: Boolean,
        density: Density,
        onCenterCalculated: (Dp) -> Unit,
    ) = if (shouldCalculateCenter) {
            this.then(
                Modifier.onGloballyPositioned { coordinates ->
                    val calculatedOffset = with(density) { (coordinates.size.width.toDp()) / 4 }
                    onCenterCalculated(calculatedOffset)
                }
            )
        } else this

    fun adjustTextStyle(
        result: TextLayoutResult,
        currentStyle: TextStyle,
        fallbackFontSize: TextUnit,
        fontWeight: FontWeight,
        color: Color
    ): TextStyle = if (result.didOverflowWidth) {
        currentStyle.copy(
            fontSize = calculateAdjustedFontSize(currentStyle.fontSize, fallbackFontSize),
            fontWeight = fontWeight,
            color = color
        )
    } else {
        currentStyle
    }

    private fun calculateAdjustedFontSize(currentFontSize: TextUnit, fallbackFontSize: TextUnit): TextUnit {
        return currentFontSize.takeIf { !it.isUnspecified }?.let { it * 0.89f } ?: fallbackFontSize
    }
}