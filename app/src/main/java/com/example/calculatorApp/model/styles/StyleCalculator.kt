package com.example.calculatorApp.model.styles

import com.example.calculatorApp.annotations.ConceptClass
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle

sealed class StyleCalculator(
    override val buttonStyle: ElementCategoryStyleCollection<ElementColorStyle>,
    override val displayStyle: ElementCategoryStyleCollection<ElementColorStyle>,
    override val rowStyle: ElementCategoryStyleCollection<ElementColorStyle>,
) : Style {

    data object Standard : StyleCalculator(
        buttonStyle = StylesButton.iButtonStyle,
        displayStyle = StylesDisplay.iDisplayStyleInput,
        rowStyle = StylesRow.iRowStyle
    )

    @ConceptClass
    data object Scientific : StyleCalculator(
        buttonStyle = StylesButton.iButtonStyle,
        displayStyle = StylesDisplay.iDisplayStyleInput,
        rowStyle = StylesRow.iRowStyle
    )
}