package com.example.calculatorApp.model.symbols

import androidx.annotation.DrawableRes

enum class SymbolButton(
    override val label: String,
    @DrawableRes override val iconRes: Int? = null
) : Symbol {

    // binary
    DIVISION("÷"),
    MULTIPLICATION("×"),
    SUBTRACTION("-"),
    ADDITION("+"),

    // unary
    PLUS_MINUS("±"),
    PERCENT("%"),

    // control
    ALL_CLEAR("AC"),
    CLEAR("C"),
    DECIMAL(","),
    EQUALS("="),

    // number
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),

    // parenthesis
    OPEN_PARENT("("),
    CLOSE_PARENT(")");
}