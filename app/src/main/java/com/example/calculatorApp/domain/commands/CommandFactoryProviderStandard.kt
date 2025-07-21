package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.di.ButtonCategoryHiltKey
import javax.inject.Inject

class CommandFactoryProviderStandard @Inject constructor(
    private val factories: Map<ButtonCategoryHiltKey, @JvmSuppressWildcards CommandFactory>
) : CommandFactoryProvider {

    override fun getFactory(buttonCategory: ButtonCategoryHiltKey): CommandFactory {
        return factories[buttonCategory]
            ?: throw IllegalArgumentException("No factory found for: $buttonCategory")
    }
}