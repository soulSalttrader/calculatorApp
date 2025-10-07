package com.example.calculatorApp.testData.input

import com.example.calculatorApp.model.layout.ElementLayout

sealed interface InputLayout<T : ElementLayout>: Input {
    val elementLayout: T

    data class Button<T>(
        override val elementLayout: T
    ) : InputLayout<T> where T : ElementLayout {
        override fun toString(): String = "InputLayout.Button(layout=${elementLayout})"
    }

    data class Display<T>(
        override val elementLayout: T
    ) : InputLayout<T> where T : ElementLayout {
        override fun toString(): String = "InputLayout.Display(layout=${elementLayout})"
    }

    data class Row<T>(
        override val elementLayout: T
    ) : InputLayout<T> where T : ElementLayout {
        override fun toString(): String = "InputLayout.Row(layout=${elementLayout})"
    }
}