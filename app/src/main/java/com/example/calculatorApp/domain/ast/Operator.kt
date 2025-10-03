package com.example.calculatorApp.domain.ast

import com.example.calculatorApp.model.symbols.HasSymbol
import com.example.calculatorApp.model.symbols.Symbol

sealed interface Operator : HasSymbol {

    override val symbol: Symbol
}