package com.example.calculatorApp.model.layout.button

import com.example.calculatorApp.arguments.TestArgumentsCalculatorElementLayout.layoutPropertyChecks
import com.example.calculatorApp.arguments.TestArgumentsCalculatorElementLayout.layoutTextPropertyChecks
import com.example.calculatorApp.arguments.TestArgumentsCalculatorElementLayout.provideElementLayoutTestCases
import com.example.calculatorApp.model.layout.ElementLayout
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedLayout
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputLayout
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream

class ButtonLayoutRegularTest {

    @Nested
    inner class ButtonLayoutRegularPropertiesTest {

        // Arrange:
        private fun provideArguments(): Stream<TestCase<Input, Expected>> =
            provideElementLayoutTestCases(ButtonLayoutRegular()).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should return the correct property for regular button layout`(
            testData: TestCase<InputLayout.Button<ButtonLayoutRegular>, ExpectedLayout.Button>
        ) {
            // Arrange:
            val actual = testData.input.elementLayout
            val expected = testData.expected
            val layoutChecks = layoutPropertyChecks<ExpectedLayout.Button>()
            val layoutTextChecks = layoutTextPropertyChecks<ExpectedLayout.Button>()

            // Act & Assert:
            layoutChecks.forEach { (propertyName, extractor) ->
                val (exp, act) = extractor(expected, actual)
                withClue("Failed for property: $propertyName") {
                    act shouldBe exp
                }
            }

            layoutTextChecks.forEach { (propertyName, extractor) ->
                val (exp, act) = extractor(expected, actual)
                withClue("Failed for property: $propertyName") {
                    act shouldBe exp
                }
            }
        }
    }
}