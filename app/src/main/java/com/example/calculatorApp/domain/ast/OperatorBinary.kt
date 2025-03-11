package com.example.calculatorApp.domain.ast

import com.example.calculatorApp.model.symbols.Symbol
import com.example.calculatorApp.model.symbols.SymbolButton

sealed class OperatorBinary(override val symbol: Symbol) : OperatorBase<OperatorBinary>(symbol) {
    data object Addition : OperatorBinary(SymbolButton.ADDITION)
    data object Subtraction : OperatorBinary(SymbolButton.SUBTRACTION)
    data object Multiplication : OperatorBinary(SymbolButton.MULTIPLICATION)
    data object Division : OperatorBinary(SymbolButton.DIVISION)
}