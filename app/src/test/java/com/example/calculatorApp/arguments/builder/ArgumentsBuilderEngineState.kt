package com.example.calculatorApp.arguments.builder

import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.state.HasState
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataElement.buttonsBinaryTest
import com.example.calculatorApp.testData.TestDataEngineMathStandardBinary.provideOperandsTest
import com.example.calculatorApp.testData.TestDataEngineStateStandard.buildHasState
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
    var buildHasState: (List<Token>, String, Operator?, Button, Boolean, Boolean) -> HasState = ::buildHasState,
) : ArgumentsBuilder<Input, Expected> {

    override fun provideTestCases(
        scenario: Scenario
    ): Sequence<TestCase<Input, Expected>> = sequence {
        val engineStateScenario = scenario.requireScenario<ScenarioEngineState>()

        buttonsBinary.forEach { button ->
            lastOperands.forEach { (previousOperand, lastOperand) ->
                val expression = buildExpression(previousOperand, button)
                val hasError = engineStateScenario is ScenarioEngineState.Error
                val isComputed = !hasError
                val context = engineStateScenario.factoryContext(
                    buildHasState(expression, lastOperand.toString(), button.toOperator(), button, hasError, isComputed)
                )
                yield(
                    TestCase(
                        input = engineStateScenario.factoryInput(context),
                        expected = engineStateScenario.factoryExpected(context)
                    )
                )
            }
        }
    }
}