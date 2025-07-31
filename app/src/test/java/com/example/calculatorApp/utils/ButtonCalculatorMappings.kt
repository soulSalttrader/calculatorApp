package com.example.calculatorApp.utils

import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorParenthesis
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge
import com.example.calculatorApp.ui.theme.White

object ButtonCalculatorMappings {

    val binaryVisualsMap: Map<ButtonCalculatorBinary, VisualsButton> = mapOf(
        ButtonCalculatorBinary.Addition to VisualsButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.ADDITION,
        ),
        ButtonCalculatorBinary.Subtraction to VisualsButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.SUBTRACTION,
        ),
        ButtonCalculatorBinary.Multiplication to VisualsButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.MULTIPLICATION,
        ),
        ButtonCalculatorBinary.Division to VisualsButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.DIVISION,
        ),
    )

    val unaryVisualsMap: Map<ButtonCalculatorUnary, VisualsButtonUnary> = mapOf(
        ButtonCalculatorUnary.Sign to VisualsButtonUnary(
            background = SilverGrey,
            foreground = Onyx,
            symbol = SymbolButton.SIGN,
            isPrefix = true,
        ),
        ButtonCalculatorUnary.Percentage to VisualsButtonUnary(
            background = SilverGrey,
            foreground = Onyx,
            symbol = SymbolButton.PERCENTAGE,
            isPrefix = false,
        ),
    )

    val controlVisualsMap: Map<ButtonCalculatorControl, VisualsButton> = mapOf(
        ButtonCalculatorControl.AllClear to VisualsButton(
            background = SilverGrey,
            foreground = Onyx,
            symbol = SymbolButton.ALL_CLEAR,
        ),
        ButtonCalculatorControl.Clear to VisualsButton(
            background = SilverGrey,
            foreground = Onyx,
            symbol = SymbolButton.CLEAR,
        ),
        ButtonCalculatorControl.Decimal to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.DECIMAL,
        ),
        ButtonCalculatorControl.Equals to VisualsButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.EQUALS,
        ),
    )

    val numberVisualsMap: Map<ButtonCalculatorNumber, VisualsButton> = mapOf(
        ButtonCalculatorNumber.Zero to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.ZERO,
        ),
        ButtonCalculatorNumber.One to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.ONE,
        ),
        ButtonCalculatorNumber.Two to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.TWO,
        ),
        ButtonCalculatorNumber.Three to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.THREE,
        ),
        ButtonCalculatorNumber.Four to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.FOUR,
        ),
        ButtonCalculatorNumber.Five to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.FIVE,
        ),
        ButtonCalculatorNumber.Six to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.SIX,
        ),
        ButtonCalculatorNumber.Seven to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.SEVEN,
        ),
        ButtonCalculatorNumber.Eight to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.EIGHT,
        ),
        ButtonCalculatorNumber.Nine to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.NINE,
        ),
    )

    val parenthesisVisualsMap: Map<ButtonCalculatorParenthesis, VisualsButton> = mapOf(
        ButtonCalculatorParenthesis.OpenParenthesis to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.OPEN_PARENT,
        ),
        ButtonCalculatorParenthesis.CloseParenthesis to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.CLOSE_PARENT,
        ),
    )
}
