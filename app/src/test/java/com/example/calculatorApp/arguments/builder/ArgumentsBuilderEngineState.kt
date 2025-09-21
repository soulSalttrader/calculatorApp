package com.example.calculatorApp.arguments.builder

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataElement.buttonsBinaryTest
import com.example.calculatorApp.testData.TestDataEngineMathStandardBinary.provideOperandsTest
import com.example.calculatorApp.testData.TestDataEngineStateStandard.buildTokensFrom
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.scenario.Scenario
import com.example.calculatorApp.testData.scenario.ScenarioEngineState
import com.example.calculatorApp.testData.scenario.requireScenario

class ArgumentsBuilderEngineState(
    var lastOperands: Sequence<Pair<Number, Number>> = provideOperandsTest(),
    var buttonsBinary: Sequence<Button> = buttonsBinaryTest,
    var buildExpression: (Number, Button) -> List<Token> = ::buildTokensFrom,
) : ArgumentsBuilder<Input, Expected> {

    override fun provideTestCases(
        scenario: Scenario
    ): Sequence<TestCase<Input, Expected>> = sequence {
        val engineStateScenario = scenario.requireScenario<ScenarioEngineState>()

        buttonsBinary.forEach { button ->
            lastOperands.forEach { (previousOperand, lastOperand) ->
                val expressionInput = buildExpression(previousOperand, button)

                val (contextInput, contextExpected) =
                    engineStateScenario.buildContexts(expressionInput, lastOperand.toString(), button)

                yield(
                    TestCase(
                        input = engineStateScenario.buildInput(contextInput),
                        expected = engineStateScenario.buildExpected(contextExpected)
                    )
                )
            }
        }
    }
}