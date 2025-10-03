package com.example.calculatorApp.testData.expected

import com.example.calculatorApp.model.layout.ElementLayout
import com.example.calculatorApp.model.layout.ElementLayoutPositioning
import com.example.calculatorApp.model.layout.ElementLayoutText
import com.example.calculatorApp.model.layout.HasColor

sealed interface ExpectedLayout : Expected {

    data class Button(
        private val delegateLayout: ElementLayout,
        private val delegateText: ElementLayoutText
    ) : ExpectedLayout,
        ElementLayout by delegateLayout,
        ElementLayoutText by delegateText

    data class Display(
        private val delegateLayout: ElementLayout,
        private val delegateText: ElementLayoutText
    ) : ExpectedLayout,
        ElementLayout by delegateLayout,
        ElementLayoutText by delegateText

    data class Row(
        private val delegateLayout: ElementLayout,
        private val delegatePosition: ElementLayoutPositioning,
        private val delegateColor: HasColor,
    ) : ExpectedLayout,
        ElementLayout by delegateLayout,
        ElementLayoutPositioning by delegatePosition,
        HasColor by delegateColor
}



