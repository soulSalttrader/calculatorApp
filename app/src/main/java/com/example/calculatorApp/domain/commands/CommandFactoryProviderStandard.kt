package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.di.ButtonCategoryHiltKey

class CommandFactoryProviderStandard(
    private val factories: Map<ButtonCategoryHiltKey, @JvmSuppressWildcards CommandFactory>
) : CommandFactoryProvider {

    override fun getFactory(buttonCategory: ButtonCategoryHiltKey): CommandFactory {
        return factories[buttonCategory]
            ?: throw IllegalArgumentException("No factory found for: $buttonCategory")
    }
}