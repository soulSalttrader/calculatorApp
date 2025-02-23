package com.example.calculatorApp.utils

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorParenthesis
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge

object ButtonCalculatorMappings {

    // Map for ButtonCalculatorBinary to Color (future-proofing)
    val binaryColorMap: Map<ButtonCalculatorBinary, Any> = mapOf(
        ButtonCalculatorBinary.Addition to VividGamboge,
        ButtonCalculatorBinary.Subtraction to VividGamboge,
        ButtonCalculatorBinary.Multiplication to VividGamboge,
        ButtonCalculatorBinary.Division to VividGamboge,

    )

    // Map for ButtonCalculatorBinary to SymbolButton
    val binarySymbolMap: Map<ButtonCalculatorBinary, SymbolButton> = mapOf(
        ButtonCalculatorBinary.Addition to SymbolButton.ADDITION,
        ButtonCalculatorBinary.Subtraction to SymbolButton.SUBTRACTION,
        ButtonCalculatorBinary.Multiplication to SymbolButton.MULTIPLICATION,
        ButtonCalculatorBinary.Division to SymbolButton.DIVISION,
    )

    val unaryColorMap: Map<ButtonCalculatorUnary, Any> = mapOf(
        ButtonCalculatorUnary.Sign to SilverGrey,
        ButtonCalculatorUnary.Percentage to SilverGrey,
    )

    val unarySymbolMap: Map<ButtonCalculatorUnary, SymbolButton> = mapOf(
        ButtonCalculatorUnary.Sign to SymbolButton.SIGN,
        ButtonCalculatorUnary.Percentage to SymbolButton.PERCENTAGE,
    )

    val unaryIsPrefix: Map<ButtonCalculatorUnary, Boolean> = mapOf(
        ButtonCalculatorUnary.Sign to true,
        ButtonCalculatorUnary.Percentage to true,
    )

    val unaryIsSuffix: Map<ButtonCalculatorUnary, Boolean> = mapOf(
        ButtonCalculatorUnary.Sign to false,
        ButtonCalculatorUnary.Percentage to false,
    )

    // Map for ButtonCalculatorControl to color and symbol
    val controlColorMap: Map<ButtonCalculatorControl, Any> = mapOf(
        ButtonCalculatorControl.Decimal to Onyx,
        ButtonCalculatorControl.AllClear to SilverGrey,
        ButtonCalculatorControl.Clear to SilverGrey,
        ButtonCalculatorControl.Equals to VividGamboge,
    )

    val controlSymbolMap: Map<ButtonCalculatorControl, SymbolButton> = mapOf(
        ButtonCalculatorControl.AllClear to SymbolButton.ALL_CLEAR,
        ButtonCalculatorControl.Clear to SymbolButton.CLEAR,
        ButtonCalculatorControl.Decimal to SymbolButton.DECIMAL,
        ButtonCalculatorControl.Equals to SymbolButton.EQUALS
    )

    // Map for ButtonCalculatorNumber to Color (future-proofing)
    val numberColorMap: Map<ButtonCalculatorNumber, Any> = mapOf(
        ButtonCalculatorNumber.Zero to Onyx,
        ButtonCalculatorNumber.One to Onyx,
        ButtonCalculatorNumber.Two to Onyx,
        ButtonCalculatorNumber.Three to Onyx,
        ButtonCalculatorNumber.Four to Onyx,
        ButtonCalculatorNumber.Five to Onyx,
        ButtonCalculatorNumber.Six to Onyx,
        ButtonCalculatorNumber.Seven to Onyx,
        ButtonCalculatorNumber.Eight to Onyx,
        ButtonCalculatorNumber.Nine to Onyx
    )

    // Map for ButtonCalculatorNumber to SymbolButton
    val numberSymbolMap: Map<ButtonCalculatorNumber, SymbolButton> = mapOf(
        ButtonCalculatorNumber.Zero to SymbolButton.ZERO,
        ButtonCalculatorNumber.One to SymbolButton.ONE,
        ButtonCalculatorNumber.Two to SymbolButton.TWO,
        ButtonCalculatorNumber.Three to SymbolButton.THREE,
        ButtonCalculatorNumber.Four to SymbolButton.FOUR,
        ButtonCalculatorNumber.Five to SymbolButton.FIVE,
        ButtonCalculatorNumber.Six to SymbolButton.SIX,
        ButtonCalculatorNumber.Seven to SymbolButton.SEVEN,
        ButtonCalculatorNumber.Eight to SymbolButton.EIGHT,
        ButtonCalculatorNumber.Nine to SymbolButton.NINE
    )

    val parenthesisColorMap: Map<ButtonCalculatorParenthesis, Any> = mapOf(
        ButtonCalculatorParenthesis.OpenParenthesis to Onyx,
        ButtonCalculatorParenthesis.CloseParenthesis to Onyx,
    )

    val parenthesisSymbolMap: Map<ButtonCalculatorParenthesis, SymbolButton> = mapOf(
        ButtonCalculatorParenthesis.OpenParenthesis to SymbolButton.OPEN_PARENT,
        ButtonCalculatorParenthesis.CloseParenthesis to SymbolButton.CLOSE_PARENT,
    )

    val buttonButtonMap: Map<Button, Button> = mapOf(
        ButtonCalculatorBinary.Addition to ButtonCalculatorBinary.Addition,
        ButtonCalculatorBinary.Subtraction to ButtonCalculatorBinary.Subtraction,
        ButtonCalculatorBinary.Multiplication to ButtonCalculatorBinary.Multiplication,
        ButtonCalculatorBinary.Division to ButtonCalculatorBinary.Division,

        ButtonCalculatorUnary.Sign to ButtonCalculatorUnary.Sign,
        ButtonCalculatorUnary.Percentage to ButtonCalculatorUnary.Percentage,

        ButtonCalculatorControl.AllClear to ButtonCalculatorControl.AllClear,
        ButtonCalculatorControl.Clear to ButtonCalculatorControl.Clear,
        ButtonCalculatorControl.Decimal to ButtonCalculatorControl.Decimal,
        ButtonCalculatorControl.Equals to ButtonCalculatorControl.Equals,

        ButtonCalculatorNumber.Zero to ButtonCalculatorNumber.Zero,
        ButtonCalculatorNumber.One to ButtonCalculatorNumber.One,
        ButtonCalculatorNumber.Two to ButtonCalculatorNumber.Two,
        ButtonCalculatorNumber.Three to ButtonCalculatorNumber.Three,
        ButtonCalculatorNumber.Four to ButtonCalculatorNumber.Four,
        ButtonCalculatorNumber.Five to ButtonCalculatorNumber.Five,
        ButtonCalculatorNumber.Six to ButtonCalculatorNumber.Six,
        ButtonCalculatorNumber.Seven to ButtonCalculatorNumber.Seven,
        ButtonCalculatorNumber.Eight to ButtonCalculatorNumber.Eight,
        ButtonCalculatorNumber.Nine to ButtonCalculatorNumber.Nine,

        ButtonCalculatorParenthesis.CloseParenthesis to ButtonCalculatorParenthesis.CloseParenthesis,
        ButtonCalculatorParenthesis.OpenParenthesis to ButtonCalculatorParenthesis.OpenParenthesis,
    )
}
