package com.example.calculatorApp.testData.input

import com.example.calculatorApp.model.layout.ElementLayout
import com.example.calculatorApp.model.layout.ElementLayoutText

sealed interface InputLayout<T>: Input where T : ElementLayout, T : ElementLayoutText {
    val elementLayout: T

    data class Button<T>(
        override val elementLayout: T
    ) : InputLayout<T> where T : ElementLayout, T : ElementLayoutText
}