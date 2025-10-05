package com.example.calculatorApp.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

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
}