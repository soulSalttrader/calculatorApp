package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.model.state.CalculatorState

class CommandChain(private vararg val commands: Command) : Command {
    override fun execute(state: CalculatorState): CalculatorState {
        return commands.fold(state) { currentState, command -> command.execute(currentState) }
    }
}