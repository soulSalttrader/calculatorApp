package com.example.calculatorApp.model.elements.button

import com.example.calculatorApp.model.elements.ElementCategoryStyleBase
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.symbols.SymbolButton

class ButtonCategoryStyleControl(
    baseStyle: ElementColorStyle,
    decimalStyle: ElementColorStyle? = null,
    equalsStyle: ElementColorStyle? = null,
) : ElementCategoryStyleBase<ElementColorStyle>(
    baseStyle,
    mapOf(
        SymbolButton.DECIMAL.label to (decimalStyle ?: baseStyle), // Decimal button is categorized as a control button but should use number button color scheme
        SymbolButton.EQUALS.label to (equalsStyle ?: baseStyle)
    )
)