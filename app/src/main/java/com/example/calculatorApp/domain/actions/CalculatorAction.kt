package com.example.calculatorApp.domain.actions

import com.example.calculatorApp.model.elements.button.Button

sealed class CalculatorAction {

    data class ButtonPressed(val button: Button) : CalculatorAction()
}