package com.example.calculatorApp.domain.ast

import com.example.calculatorApp.TestData.TestDataTokenizerStandard
import com.example.calculatorApp.arguments.TestArgumentsTokenizerStandard
import com.example.calculatorApp.arguments.TestArgumentsTokenizerStandard.provideSymbolLabels
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorParenthesis
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream

class TokenizerStandardTest {

    @Nested
    inner class Tokenize {

        private lateinit var tokenizer: Tokenizer

        // Arrange:
        private fun provideArgumentsNumbers(): Stream<TestDataTokenizerStandard> {
            return TestArgumentsTokenizerStandard
                .provide(ButtonCalculatorNumber::class).asStream()
        }

        // Arrange:
        private fun provideArgumentsBinary(): Stream<TestDataTokenizerStandard> {
            return TestArgumentsTokenizerStandard
                .provide(ButtonCalculatorBinary::class).asStream()
        }

        // Arrange:
        private fun provideArgumentsParenthesis(): Stream<TestDataTokenizerStandard> {
            return TestArgumentsTokenizerStandard
                .provide(ButtonCalculatorParenthesis::class).asStream()
        }

        // Arrange:
        private fun provideArgumentsUnary(): Stream<TestDataTokenizerStandard> {
            return TestArgumentsTokenizerStandard
                .provide(ButtonCalculatorUnary::class).asStream()
        }

        @BeforeEach
        fun setUp() {
            tokenizer = TokenizerStandard()
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsNumbers")
        fun `tokenize single number`(
            dataTest: TestDataTokenizerStandard
        ) {
            // Arrange:
            val number = dataTest.expression
            // Act:
            val tokenized = tokenizer.tokenize(number)
            // Assert:
            dataTest.expectedNumber() shouldBe tokenized
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsBinary")
        fun `tokenize simple binary operator`(
            dataTest: TestDataTokenizerStandard
        ) {
            // Arrange:
            val binary = dataTest.expression
            // Act:
            val tokenized = tokenizer.tokenize(binary)
            // Assert:
            dataTest.expectedBinary() shouldBe tokenized
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsUnary")
        fun `tokenize unary operators`(
            dataTest: TestDataTokenizerStandard
        ) {
            // Arrange:
            val unary = dataTest.expression
            // Act:
            val tokenized = tokenizer.tokenize(unary)
            // Assert:
            dataTest.expectedUnary() shouldBe tokenized
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsParenthesis")
        fun `tokenize parentheses`(
            dataTest: TestDataTokenizerStandard
        ) {
            // Arrange:
            val parenthesis = dataTest.expression
            // Act:
            val tokenized = tokenizer.tokenize(parenthesis)
            // Assert:
            dataTest.expectedParenthesis() shouldBe tokenized
        }

        @Test
        fun `tokenize multiple mixed tokens`() {
            val mixed = provideSymbolLabels().toList()
            // Act:
            val tokenized = tokenizer.tokenize(mixed)
            // Assert:
            TestDataTokenizerStandard.expectedMixed() shouldBe tokenized
        }

        @ParameterizedTest
        @CsvSource(
            "´",
            "¬",
            "ˆ",
            "ß",
            "˚"
        )
        fun `tokenize invalid token should throw exception`(
            dataTest: String
        ) {
            // Arrange:
            val invalidInput = listOf(dataTest)
            // Act & Assert:
            invalidInput.let {
                val exception = shouldThrow<IllegalArgumentException> {
                    tokenizer.tokenize(invalidInput)
                }

                exception.message shouldBe "Invalid token: ${it.joinToString()}"
            }
        }
    }
}