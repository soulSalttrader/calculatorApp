package com.example.calculatorApp.di.module

import com.example.calculatorApp.domain.ast.Parser
import com.example.calculatorApp.domain.ast.ParserToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleParser {

    @Singleton
    @Provides
    fun provideParser(): Parser = ParserToken()
}