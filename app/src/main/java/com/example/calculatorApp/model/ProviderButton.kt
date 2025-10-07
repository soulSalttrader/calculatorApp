package com.example.calculatorApp.model

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.model.layout.button.ButtonLayoutWide
import com.example.calculatorApp.model.state.CalculatorStateDomain
import com.example.calculatorApp.model.styles.Style
import com.example.calculatorApp.model.symbols.SymbolButton

sealed class ProviderButton : Provider<ButtonData> {

    object ClearButton : ProviderButton() {
        override fun create(state: CalculatorStateDomain, style: Style): ButtonData {
            val actualButton =
                if (shouldUseAllClear(state)) ButtonCalculatorControl.AllClear else ButtonCalculatorControl.Clear
            return ButtonData(actualButton)
        }

        private fun shouldUseAllClear(state: CalculatorStateDomain): Boolean =
            state.lastOperand == SymbolButton.ZERO.label || state.lastOperator == null
    }

    object ZeroButton : ProviderButton() {
        override fun create(state: CalculatorStateDomain, style: Style): ButtonData =
            ButtonData(ButtonCalculatorNumber.Zero, ButtonLayoutWide())
    }

    data class StandardButton(val button: Button) : ProviderButton() {
        override fun create(state: CalculatorStateDomain, style: Style): ButtonData =
            ButtonData(button)
    }

    companion object {
        fun getDynamicButton(button: Button, state: CalculatorStateDomain, style: Style): ButtonData {
            return when (button) {
                ButtonCalculatorControl.AllClear,
                ButtonCalculatorControl.Clear -> ClearButton.create(state, style)
                ButtonCalculatorNumber.Zero   -> ZeroButton.create(state, style)
                else                          -> StandardButton(button).create(state, style)
            }
        }
    }
}