package com.example.calculatorApp.domain.ast

import com.example.calculatorApp.model.symbols.Symbol
import com.example.calculatorApp.model.symbols.SymbolButton

sealed class OperatorParenthesis(override val symbol: Symbol) : OperatorBase<OperatorParenthesis>(symbol) {
    data object Open : OperatorParenthesis(SymbolButton.OPEN_PARENT)
    data object Close : OperatorParenthesis(SymbolButton.CLOSE_PARENT)
}