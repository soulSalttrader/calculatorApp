package com.example.calculatorApp.arguments

import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge
import com.example.calculatorApp.utils.ButtonCalculatorList.allButtons
import com.example.calculatorApp.utils.ButtonCalculatorList.binary
import com.example.calculatorApp.utils.ButtonCalculatorList.controls
import com.example.calculatorApp.utils.ButtonCalculatorList.numbers
import com.example.calculatorApp.utils.ButtonCalculatorList.parenthesis
import com.example.calculatorApp.utils.ButtonCalculatorList.unary
import com.example.calculatorApp.utils.ButtonCalculatorMappings
import org.junit.jupiter.params.provider.Arguments

object TestArgumentsButton : TestArguments {

    private fun <T, R> provideButtonProperties(
        buttons: Array<T>,
        propertyMapping: (T) -> R
    ) = buttons.map { button -> Arguments.of(button, propertyMapping(button)) }.stream()

    fun provideArithmeticColors() = provideButtonProperties(
        buttons = binary,
        propertyMapping = { ButtonCalculatorMappings.binaryColorMap[it] ?: VividGamboge }
    )

    fun provideArithmeticSymbols() = provideButtonProperties(
        buttons = binary,
        propertyMapping = { ButtonCalculatorMappings.binarySymbolMap[it] }
    )

    fun provideUnaryColors() = provideButtonProperties(
        buttons = unary,
        propertyMapping = { ButtonCalculatorMappings.unaryColorMap[it] ?: SilverGrey }
    )

    fun provideUnarySymbols() = provideButtonProperties(
        buttons = unary,
        propertyMapping = { ButtonCalculatorMappings.unarySymbolMap[it] }
    )

    fun provideIsPrefix() = provideButtonProperties(
        buttons = unary,
        propertyMapping = { ButtonCalculatorMappings.unaryIsPrefix[it] }
    )

    fun provideIsSuffix() = provideButtonProperties(
        buttons = unary,
        propertyMapping = { ButtonCalculatorMappings.unaryIsSuffix[it] }
    )

    fun provideControlColors() = provideButtonProperties(
        buttons = controls,
        propertyMapping = { ButtonCalculatorMappings.controlColorMap[it] ?: SilverGrey }
    )

    fun provideControlSymbols() = provideButtonProperties(
        buttons = controls,
        propertyMapping = { ButtonCalculatorMappings.controlSymbolMap[it] }
    )

    fun provideNumberColors() = provideButtonProperties(
        buttons = numbers,
        propertyMapping = { ButtonCalculatorMappings.numberColorMap[it] ?: Onyx }
    )

    fun provideNumberSymbols() = provideButtonProperties(
        buttons = numbers,
        propertyMapping = { ButtonCalculatorMappings.numberSymbolMap[it] }
    )

    fun provideParenthesisColors() = provideButtonProperties(
        buttons = parenthesis,
        propertyMapping = { ButtonCalculatorMappings.parenthesisColorMap[it] ?: SilverGrey }
    )

    fun provideParenthesisSymbols() = provideButtonProperties(
        buttons = parenthesis,
        propertyMapping = { ButtonCalculatorMappings.parenthesisSymbolMap[it] }
    )

    fun provideButtonButton() = provideButtonProperties(
        buttons = allButtons,
        propertyMapping = { ButtonCalculatorMappings.buttonButtonMap[it] }
    )
}
