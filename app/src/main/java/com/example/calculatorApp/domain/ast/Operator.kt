package com.example.calculatorApp.domain.ast

import com.example.calculatorApp.model.symbols.Symbol

sealed interface Operator {
    val symbol: Symbol
}