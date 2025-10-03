package com.example.calculatorApp.testData.input

import com.example.calculatorApp.model.layout.ElementLayout

sealed interface InputLayout<T : ElementLayout>: Input {
    val elementLayout: T

    data class Button<T>(
        override val elementLayout: T
    ) : InputLayout<T> where T : ElementLayout

    data class Display<T>(
        override val elementLayout: T
    ) : InputLayout<T> where T : ElementLayout

    data class Row<T>(
        override val elementLayout: T
    ) : InputLayout<T> where T : ElementLayout
}