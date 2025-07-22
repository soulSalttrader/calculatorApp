package com.example.calculatorApp.di.module

import com.example.calculatorApp.domain.EngineMath
import com.example.calculatorApp.domain.EngineMathStandard
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleEngineMath {

    @Singleton
    @Provides
    fun provideEngineMath(): EngineMath = EngineMathStandard()
}