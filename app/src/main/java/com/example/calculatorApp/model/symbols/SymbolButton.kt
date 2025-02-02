package com.example.calculatorApp.model.symbols

import androidx.annotation.DrawableRes

enum class SymbolButton(
    override val label: String,
    @DrawableRes override val iconRes: Int? = null
) : Symbol {

    // control
    ALL_CLEAR("AC"),
    CLEAR("C"),
    PLUS_MINUS("±"),
    PERCENT("%"),
    DECIMAL(","),

    // arithmetic
    DIVISION("÷"),
    MULTIPLICATION("×"),
    SUBTRACTION("-"),
    ADDITION("+"),
    EQUALS("="),

    // number
    DIGIT_0("0"),
    DIGIT_1("1"),
    DIGIT_2("2"),
    DIGIT_3("3"),
    DIGIT_4("4"),
    DIGIT_5("5"),
    DIGIT_6("6"),
    DIGIT_7("7"),
    DIGIT_8("8"),
    DIGIT_9("9");
}