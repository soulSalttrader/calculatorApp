package com.example.calculatorApp.model.elements.button

import com.example.calculatorApp.model.elements.Element
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.state.CalculatorStateDomain
import com.example.calculatorApp.model.symbols.HasSymbol
import com.example.calculatorApp.model.symbols.Symbol

interface Button :
    Element<ElementCategory<ElementColorStyle>, ElementCategoryStyleCollection<ElementColorStyle>, ElementColorStyle>,
    HasSymbol
{

    override val symbol: Symbol
    fun shouldHighlight(state: CalculatorStateDomain): Boolean
}