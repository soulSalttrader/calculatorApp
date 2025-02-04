package com.example.calculatorApp.arguments

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge
import com.example.calculatorApp.utils.ButtonCalculatorList
import com.example.calculatorApp.utils.ButtonCalculatorMappings
import org.junit.jupiter.params.provider.Arguments

data class TestArgumentsButton(
    val arithmetics: Array<ButtonCalculatorArithmetic> = ButtonCalculatorList.arithmetics,
    val controls: Array<ButtonCalculatorControl> = ButtonCalculatorList.controls,
    val numbers: Array<ButtonCalculatorNumber> = ButtonCalculatorList.numbers,
) : TestArguments {

    private fun <T> provideButtonColors(buttons: Array<T>, colorMapping: (T) -> Any) =
        buttons.map { button -> Arguments.of(button, colorMapping(button)) }.stream()

    private fun <T> provideButtonSymbols(buttons: Array<T>, symbolMapping: (T) -> SymbolButton?) =
        buttons.map { button -> Arguments.of(button, symbolMapping(button)) }.stream()

    fun provideArithmeticColors() = provideButtonColors(
        buttons = arithmetics,
        colorMapping = { ButtonCalculatorMappings.arithmeticColorMap[it] ?: VividGamboge }
    )

    fun provideArithmeticSymbols() = provideButtonSymbols(
        buttons = arithmetics,
        symbolMapping = { ButtonCalculatorMappings.arithmeticSymbolMap[it] }
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TestArgumentsButton

        return arithmetics.contentEquals(other.arithmetics)
    }

    override fun hashCode(): Int {
        return arithmetics.contentHashCode()
    }
}
