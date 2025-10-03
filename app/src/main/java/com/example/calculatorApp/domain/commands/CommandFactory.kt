package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.action.CalculatorAction

interface CommandFactory {

    fun createCommand(action: CalculatorAction): Command
}