package com.example.calculatorApp.domain.ast

import com.example.calculatorApp.testData.TestDataParserToken
import com.example.calculatorApp.arguments.TestArgumentsParserToken
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream

class ParserTokenTest {

    @Nested
    inner class Parse {

        private lateinit var parser: Parser

        // Arrange:
        private fun provideArguments(): Stream<TestDataParserToken> {
            return TestArgumentsParserToken.provideDataTestParserToken().asStream()
        }

        @BeforeEach
        fun setUp() {
            parser = ParserToken()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `parse single number correctly`(
            testData: TestDataParserToken
        ) {
            // Act:
            val parsedToken = parser.parse(listOf(testData.number))

            // Assert:
            testData.expectedSingleNumber() shouldBe parsedToken
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `parse simple binary expression correctly`(
            testData: TestDataParserToken
        ) {
            // Act:
            val parsedTokens = parser.parse(
                listOf(
                    testData.firstOperator,
                    testData.number,
                    testData.number,
                )
,            )

            // Assert:
            testData.expectedSimpleBinaryExpression() shouldBe parsedTokens
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `parse expression with multiple operators`(
            testData: TestDataParserToken
        ) {
            // Act:
            val parsedTokens = parser.parse(
                listOf(
                    testData.firstOperator,
                    testData.number,
                    testData.number,

                    testData.firstOperator,
                    testData.number,
                )
            )

            // Assert:
            testData.expectedMultipleOperators() shouldBe parsedTokens
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `parse expression with different precedence`(
            testData: TestDataParserToken
        ) {
            // Act:
            val parsedTokens = parser.parse(
                listOf(
                    testData.firstOperator,
                    testData.number,
                    testData.number,

                    testData.secondOperator,
                    testData.number,
                )
            )

            // Assert:
            testData.expectedDifferentPrecedence() shouldBe parsedTokens
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `parse malformed expression should throw exception`(
            tokens: TestDataParserToken
        ) {
            // Arrange & Act & Assert:
            val exception = shouldThrow<IllegalArgumentException> {
                parser.parse(listOf(tokens.secondOperator, tokens.firstOperator))
            }

            exception.message shouldBe "Malformed expression"
        }

        @Test
        fun `parse invalid token should throw exception`() {
            // Arrange:
            val token = mockk<Token>()

            // Act & Assert:
            val exception = shouldThrow<IllegalArgumentException> {
                parser.parse(listOf(token))
            }

            exception.message shouldBe "Malformed expression"
        }
    }
}