package com.example.calculatorApp.domain.action

import com.example.calculatorApp.di.DiUtils.toButtonCategoryHiltKey
import com.example.calculatorApp.domain.action.ActionUtils.shouldResetTextSize
import com.example.calculatorApp.domain.commands.CommandFactoryProvider
import com.example.calculatorApp.model.state.CalculatorStateDomain

class CalculatorActionHandlerStandard(
    private val factoryProvider: CommandFactoryProvider,
) : CalculatorActionHandler {

    override fun handleAction(action: CalculatorAction, state: CalculatorStateDomain): CalculatorActionHandlerData {
        require(action is CalculatorAction.ButtonPressed) { "Unsupported action: $action" }

        val commandFactory = factoryProvider.getFactory(action.button.getCategory().toButtonCategoryHiltKey())
        val command = commandFactory.createCommand(action)
        val newState = command.execute(state).copy(activeButton = action.button)

        return CalculatorActionHandlerDataStandard(
            newState,
            action.shouldResetTextSize()
        )
    }
}