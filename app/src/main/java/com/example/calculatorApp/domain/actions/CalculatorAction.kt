package com.example.calculatorApp.domain.actions

import com.example.calculatorApp.model.elements.button.Button

sealed class CalculatorAction {

    abstract val button: Button
    data class ButtonPressed(override val button: Button) : CalculatorAction()
}