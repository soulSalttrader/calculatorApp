package com.example.calculatorApp.arguments.builder

import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.scenario.Scenario

interface ArgumentsBuilder<InputT : Input, ExpectedT : Expected> {
    fun provideTestCases(scenario: Scenario): Sequence<TestCase<InputT, ExpectedT>>
}