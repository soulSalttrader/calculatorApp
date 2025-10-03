package com.example.calculatorApp.di.module

import com.example.calculatorApp.di.ButtonCategoryHiltKey
import com.example.calculatorApp.domain.commands.CommandFactory
import com.example.calculatorApp.domain.commands.CommandFactoryProvider
import com.example.calculatorApp.domain.commands.CommandFactoryProviderStandard
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleCommandFactoryProvider {

    @Provides
    @Singleton
    fun provideCommandFactoryProvider(
        factories: Map<ButtonCategoryHiltKey, @JvmSuppressWildcards CommandFactory>
    ): CommandFactoryProvider = CommandFactoryProviderStandard(factories)
}