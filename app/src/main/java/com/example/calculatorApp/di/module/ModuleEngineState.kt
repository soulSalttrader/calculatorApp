package com.example.calculatorApp.di.module

import com.example.calculatorApp.domain.EngineMath
import com.example.calculatorApp.domain.EngineNode
import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.domain.EngineStateStandard
import com.example.calculatorApp.domain.ast.Parser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleEngineState {

    @Singleton
    @Provides
    fun provideEngineState(
        engineMath: EngineMath,
        engineNode: EngineNode,
        parser: Parser,
    ): EngineState = EngineStateStandard(engineMath, engineNode, parser)
}