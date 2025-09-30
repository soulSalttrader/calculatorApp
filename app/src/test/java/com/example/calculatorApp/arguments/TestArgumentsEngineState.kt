package com.example.calculatorApp.arguments

import com.example.calculatorApp.arguments.builder.ArgumentsBuilder
import com.example.calculatorApp.arguments.builder.ArgumentsBuilderEngineState
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.scenario.engineState.EngineState

object TestArgumentsEngineState : TestArguments {

    fun provideEngineStateBinaryTestCases(
        scenario: EngineState,
        builder: ArgumentsBuilder<Input, Expected> = ArgumentsBuilderEngineState()
    ): Sequence<TestCase<Input, Expected>> =
        builder.provideTestCases(scenario)

    fun provideEngineStateUnaryTestCases(
        scenario: EngineState,
        builder: ArgumentsBuilder<Input, Expected> = ArgumentsBuilderEngineState()
    ): Sequence<TestCase<Input, Expected>> =
        builder.provideTestCases(scenario)

    fun provideEngineStateControlTestCases(
        scenario: EngineState,
        builder: ArgumentsBuilder<Input, Expected> = ArgumentsBuilderEngineState()
    ): Sequence<TestCase<Input, Expected>> =
        builder.provideTestCases(scenario)

    fun provideEngineStateNumberTestCases(
        scenario: EngineState,
        builder: ArgumentsBuilder<Input, Expected> = ArgumentsBuilderEngineState()
    ): Sequence<TestCase<Input, Expected>> =
        builder.provideTestCases(scenario)
}