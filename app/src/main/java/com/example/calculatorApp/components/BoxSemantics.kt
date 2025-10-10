package com.example.calculatorApp.components

import androidx.compose.ui.semantics.Role

data class BoxSemantics(
    val role: Role? = null,
    val contentDescription: String? = null
)