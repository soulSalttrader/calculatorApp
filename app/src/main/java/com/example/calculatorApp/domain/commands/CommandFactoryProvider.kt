package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.di.ButtonCategoryHiltKey

interface CommandFactoryProvider {

    fun getFactory(buttonCategory: ButtonCategoryHiltKey): CommandFactory
}