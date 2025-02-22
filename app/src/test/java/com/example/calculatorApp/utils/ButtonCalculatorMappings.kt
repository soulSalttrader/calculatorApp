package com.example.calculatorApp.utils

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge

object ButtonCalculatorMappings {

    // Map for ButtonCalculatorArithmetic to Color (future-proofing)
    val arithmeticColorMap: Map<ButtonCalculatorBinary, Any> = mapOf(
        ButtonCalculatorBinary.Addition to VividGamboge,
        ButtonCalculatorBinary.Subtraction to VividGamboge,
        ButtonCalculatorBinary.Multiplication to VividGamboge,
        ButtonCalculatorBinary.Division to VividGamboge,
        ButtonCalculatorBinary.Equals to VividGamboge
    )

    // Map for ButtonCalculatorArithmetic to SymbolButton
    val arithmeticSymbolMap: Map<ButtonCalculatorBinary, SymbolButton> = mapOf(
        ButtonCalculatorBinary.Addition to SymbolButton.ADDITION,
        ButtonCalculatorBinary.Subtraction to SymbolButton.SUBTRACTION,
        ButtonCalculatorBinary.Multiplication to SymbolButton.MULTIPLICATION,
        ButtonCalculatorBinary.Division to SymbolButton.DIVISION,
        ButtonCalculatorBinary.Equals to SymbolButton.EQUALS
    )

    val unaryColorMap: Map<ButtonCalculatorUnary, Any> = mapOf(
        ButtonCalculatorUnary.Sign to SilverGrey,
        ButtonCalculatorUnary.Percentage to SilverGrey,
    )

    val unarySymbolMap: Map<ButtonCalculatorUnary, SymbolButton> = mapOf(
        ButtonCalculatorUnary.Sign to SymbolButton.PLUS_MINUS,
        ButtonCalculatorUnary.Percentage to SymbolButton.PERCENT,
    )

    // Map for ButtonCalculatorControl to color and symbol
    val controlColorMap: Map<ButtonCalculatorControl, Any> = mapOf(
        ButtonCalculatorControl.Decimal to Onyx,
        ButtonCalculatorControl.AllClear to SilverGrey,
        ButtonCalculatorControl.Clear to SilverGrey,
    )

    val controlSymbolMap: Map<ButtonCalculatorControl, SymbolButton> = mapOf(
        ButtonCalculatorControl.AllClear to SymbolButton.ALL_CLEAR,
        ButtonCalculatorControl.Clear to SymbolButton.CLEAR,
        ButtonCalculatorControl.Decimal to SymbolButton.DECIMAL
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

    val buttonButtonMap: Map<Button, Button> = mapOf(
        ButtonCalculatorBinary.Addition to ButtonCalculatorBinary.Addition,
        ButtonCalculatorBinary.Subtraction to ButtonCalculatorBinary.Subtraction,
        ButtonCalculatorBinary.Multiplication to ButtonCalculatorBinary.Multiplication,
        ButtonCalculatorBinary.Division to ButtonCalculatorBinary.Division,
        ButtonCalculatorBinary.Equals to ButtonCalculatorBinary.Equals,

        ButtonCalculatorUnary.Sign to ButtonCalculatorUnary.Sign,
        ButtonCalculatorUnary.Percentage to ButtonCalculatorUnary.Percentage,

        ButtonCalculatorControl.AllClear to ButtonCalculatorControl.AllClear,
        ButtonCalculatorControl.Clear to ButtonCalculatorControl.Clear,
        ButtonCalculatorControl.Decimal to ButtonCalculatorControl.Decimal,

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
    )
}
