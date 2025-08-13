package com.example.calculatorApp.model.symbols

import androidx.annotation.DrawableRes

enum class SymbolButton(
    override val label: String,
    val category: SymbolCategory,
    @DrawableRes override val iconRes: Int? = null
) : Symbol {

    DIVISION("÷", SymbolCategory.BINARY),
    MULTIPLICATION("×", SymbolCategory.BINARY),
    SUBTRACTION("-", SymbolCategory.BINARY),
    ADDITION("+", SymbolCategory.BINARY),

    SIGN("±", SymbolCategory.UNARY_PREFIX),
    PERCENTAGE("%", SymbolCategory.UNARY_SUFFIX),

    ALL_CLEAR("AC", SymbolCategory.CONTROL),
    CLEAR("C", SymbolCategory.CONTROL),
    DECIMAL(",", SymbolCategory.CONTROL),
    EQUALS("=", SymbolCategory.CONTROL),

    ZERO("0", SymbolCategory.NUMBER),
    ONE("1", SymbolCategory.NUMBER),
    TWO("2", SymbolCategory.NUMBER),
    THREE("3", SymbolCategory.NUMBER),
    FOUR("4", SymbolCategory.NUMBER),
    FIVE("5", SymbolCategory.NUMBER),
    SIX("6", SymbolCategory.NUMBER),
    SEVEN("7", SymbolCategory.NUMBER),
    EIGHT("8", SymbolCategory.NUMBER),
    NINE("9", SymbolCategory.NUMBER),

    OPEN_PARENT("(", SymbolCategory.PARENTHESIS),
    CLOSE_PARENT(")", SymbolCategory.PARENTHESIS);
}