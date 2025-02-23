package com.example.calculatorApp.arguments

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge
import com.example.calculatorApp.utils.ButtonCalculatorList.allButtons
import com.example.calculatorApp.utils.ButtonCalculatorList.binary
import com.example.calculatorApp.utils.ButtonCalculatorList.controls
import com.example.calculatorApp.utils.ButtonCalculatorList.numbers
import com.example.calculatorApp.utils.ButtonCalculatorList.unary
import com.example.calculatorApp.utils.ButtonCalculatorMappings
import org.junit.jupiter.params.provider.Arguments

object TestArgumentsButton : TestArguments {

    private fun <T> provideButtonColors(buttons: Array<T>, colorMapping: (T) -> Any) =
        buttons.map { button -> Arguments.of(button, colorMapping(button)) }.stream()

    private fun <T> provideButtonSymbols(buttons: Array<T>, symbolMapping: (T) -> SymbolButton?) =
        buttons.map { button -> Arguments.of(button, symbolMapping(button)) }.stream()

    private fun <T> provideButtonButtons(buttons: Array<T>, buttonMapping: (T) -> Button?) =
        buttons.map { button -> Arguments.of(button, buttonMapping(button)) }.stream()

    fun provideArithmeticColors() = provideButtonColors(
        buttons = binary,
        colorMapping = { ButtonCalculatorMappings.binaryColorMap[it] ?: VividGamboge }
    )

    fun provideArithmeticSymbols() = provideButtonSymbols(
        buttons = binary,
        symbolMapping = { ButtonCalculatorMappings.binarySymbolMap[it] }
    )

    fun provideUnaryColors() = provideButtonColors(
        buttons = unary,
        colorMapping = { ButtonCalculatorMappings.unaryColorMap[it] ?: SilverGrey }
    )

    fun provideUnarySymbols() = provideButtonSymbols(
        buttons = unary,
        symbolMapping = { ButtonCalculatorMappings.unarySymbolMap[it] }
    )

    fun provideControlColors() = provideButtonColors(
        buttons = controls,
        colorMapping = { ButtonCalculatorMappings.controlColorMap[it] ?: SilverGrey }
    )

    fun provideControlSymbols() = provideButtonSymbols(
        buttons = controls,
        symbolMapping = { ButtonCalculatorMappings.controlSymbolMap[it] }
    )

    fun provideNumberColors() = provideButtonColors(
        buttons = numbers,
        colorMapping = { ButtonCalculatorMappings.numberColorMap[it] ?: Onyx }
    )

    fun provideNumberSymbols() = provideButtonSymbols(
        buttons = numbers,
        symbolMapping = { ButtonCalculatorMappings.numberSymbolMap[it] }
    )

    fun provideButtonButton() = provideButtonButtons(
        buttons = allButtons,
        buttonMapping = { ButtonCalculatorMappings.buttonButtonMap[it] }
    )
}
