package com.example.calculatorApp.di.module

import com.example.calculatorApp.domain.EngineMath
import com.example.calculatorApp.domain.EngineNode
import com.example.calculatorApp.domain.EngineNodeStandard
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleEngineNode {

    @Singleton
    @Provides
    fun provideEngineNode(engineMath: EngineMath): EngineNode = EngineNodeStandard(engineMath)
}