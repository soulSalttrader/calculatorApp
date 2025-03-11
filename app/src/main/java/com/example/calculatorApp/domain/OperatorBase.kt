package com.example.calculatorApp.domain

import com.example.calculatorApp.model.symbols.Symbol

sealed class OperatorBase<T : OperatorBase<T>>(override val symbol: Symbol) : Operator