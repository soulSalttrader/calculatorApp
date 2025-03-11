package com.example.calculatorApp.domain

import com.example.calculatorApp.model.symbols.Symbol
import com.example.calculatorApp.model.symbols.SymbolButton

sealed class OperatorUnary(override val symbol: Symbol) : OperatorBase<OperatorUnary>(symbol) {
    sealed class Prefix(symbol: Symbol) : OperatorUnary(symbol)   {
        data object Sign : Prefix(SymbolButton.SIGN)
    }

    sealed class Suffix(symbol: Symbol) : OperatorUnary(symbol)    {
        data object Percentage : Suffix(SymbolButton.PERCENTAGE)
    }
}