package com.example.calculatorApp.di.module

import com.example.calculatorApp.di.ButtonCategoryHiltKey
import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.domain.commands.CommandFactory
import com.example.calculatorApp.domain.commands.CommandFactoryStandard
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
object ModuleCalculatorCommandFactory {

    @Provides
    @IntoMap
    @ButtonCategoryHiltMapKey(ButtonCategoryHiltKey.BINARY)
    fun provideBinaryCommandFactory(engineState: EngineState): CommandFactory = CommandFactoryStandard(engineState)

    @Provides
    @IntoMap
    @ButtonCategoryHiltMapKey(ButtonCategoryHiltKey.UNARY)
    fun provideUnaryCommandFactory(engineState: EngineState): CommandFactory = CommandFactoryStandard(engineState)

    @Provides
    @IntoMap
    @ButtonCategoryHiltMapKey(ButtonCategoryHiltKey.CONTROL)
    fun provideControlCommandFactory(engineState: EngineState): CommandFactory = CommandFactoryStandard(engineState)

    @Provides
    @IntoMap
    @ButtonCategoryHiltMapKey(ButtonCategoryHiltKey.NUMBER)
    fun provideNumberCommandFactory(engineState: EngineState): CommandFactory = CommandFactoryStandard(engineState)
}