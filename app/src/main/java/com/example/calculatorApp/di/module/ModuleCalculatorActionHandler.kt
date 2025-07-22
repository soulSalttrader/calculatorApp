package com.example.calculatorApp.di.module

import com.example.calculatorApp.domain.action.CalculatorActionHandler
import com.example.calculatorApp.domain.action.CalculatorActionHandlerStandard
import com.example.calculatorApp.domain.commands.CommandFactoryProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleCalculatorActionHandler {

    @Provides
    @Singleton
    fun provideCalculatorActionHandler(
        factoryProvider: CommandFactoryProvider
    ): CalculatorActionHandler = CalculatorActionHandlerStandard(factoryProvider)
}