package com.example.calculatorApp.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable

data class ParamsStyledBox(
    val visuals: BoxVisuals,
    val semantics: BoxSemantics = BoxSemantics(),
    val content: @Composable BoxScope.() -> Unit
)