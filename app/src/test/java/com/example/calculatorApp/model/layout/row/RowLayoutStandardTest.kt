package com.example.calculatorApp.model.layout.row

import com.example.calculatorApp.arguments.TestArgumentsCalculatorElementLayout.layoutPositioningPropertyChecks
import com.example.calculatorApp.arguments.TestArgumentsCalculatorElementLayout.layoutPropertyChecks
import com.example.calculatorApp.arguments.TestArgumentsCalculatorElementLayout.provideElementLayoutTestCases
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

class RowLayoutStandardTest {

    @Nested
    inner class RowLayoutInputPropertiesTest {

        // Arrange:
        private fun provideArguments(): Stream<TestCase<Input, Expected>> =
            provideElementLayoutTestCases(RowLayoutStandard()).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should return the correct property for row layout`(
            testData: TestCase<InputLayout.Row<RowLayoutStandard>, ExpectedLayout.Row>
        ) {
            // Arrange:
            val actual = testData.input.elementLayout
            val expected = testData.expected
            val layoutChecks = layoutPropertyChecks<ExpectedLayout.Row>()
            val layoutPositioningChecks = layoutPositioningPropertyChecks()

            // Act & Assert:
            layoutChecks.forEach { (propertyName, extractor) ->
                val (exp, act) = extractor(expected, actual)
                withClue("Failed for property: $propertyName") {
                    act shouldBe exp
                }
            }

            layoutPositioningChecks.forEach { (propertyName, extractor) ->
                val (exp, act) = extractor(expected, actual)
                withClue("Failed for property: $propertyName") {
                    act shouldBe exp
                }
            }
        }
    }
}