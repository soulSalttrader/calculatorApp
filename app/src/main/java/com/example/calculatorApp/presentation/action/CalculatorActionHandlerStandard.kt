package com.example.calculatorApp.presentation.action

import com.example.calculatorApp.domain.actions.CalculatorAction
import com.example.calculatorApp.domain.commands.CommandFactoryProvider
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.di.DiUtils.toButtonCategoryHiltKey
import com.example.calculatorApp.model.state.CalculatorState
import javax.inject.Inject

class CalculatorActionHandlerStandard @Inject constructor(
    private val factoryProvider: CommandFactoryProvider,
) : CalculatorActionHandler {

    override fun handleAction(action: CalculatorAction, state: CalculatorState): CalculatorActionHandlerData {
        require(action is CalculatorAction.ButtonPressed) { "Unsupported action: $action" }

        val commandFactory = factoryProvider.getFactory(action.button.getCategory().toButtonCategoryHiltKey())
        val command = commandFactory.createCommand(action)
        val newState = command.execute(state).copy(activeButton = action.button)

        val shouldResetTextSize = when (action.button) {
            is ButtonCalculatorControl,
            is ButtonCalculatorNumber -> true

            else -> false
        }

        return CalculatorActionHandlerDataStandard(newState, shouldResetTextSize)
    }
}