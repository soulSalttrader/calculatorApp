package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.actions.CalculatorAction

interface CommandFactory {

    fun createCommand(action: CalculatorAction): Command
}