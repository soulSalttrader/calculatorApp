package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.actions.CalculatorAction
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary

class CommandFactoryStandard(
    private val arithmeticCommandFactory: CommandFactoryArithmetic,
    private val unaryCommandFactory: CommandFactoryUnary,
    private val controlCommandFactory: CommandFactoryControl,
    private val numberCommandFactory: CommandFactoryNumber,
) : CommandFactory {

    override fun createCommand(action: CalculatorAction): Command {
        return when (action) {
            is CalculatorAction.ButtonPressed -> handleButtonPressed(action.button)
        }
    }

    private fun handleButtonPressed(button: Button): Command {

        return when (button) {
            is ButtonCalculatorNumber -> numberCommandFactory.create(button)
            is ButtonCalculatorUnary -> unaryCommandFactory.create(button)
            is ButtonCalculatorBinary -> arithmeticCommandFactory.create(button)
            is ButtonCalculatorControl -> controlCommandFactory.create(button)
            else -> throw IllegalArgumentException("Unknown button.")
        }
    }
}