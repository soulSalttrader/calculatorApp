package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.model.elements.button.Button

interface CommandFactorySub<T : Button> {

    fun create(button: T): Command
}