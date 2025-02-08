package com.example.calculatorApp.model.layout.display

import com.example.calculatorApp.arguments.TestArgumentsDisplayLayout
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class DisplayLayoutInputTest {

    @Nested
    inner class DisplayLayoutInputPropertiesTest {

        // Arrange:
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsDisplayLayout().provideInputProperties()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should return the correct property for display layout`(
            actualProperty: Any,
            expectedProperty: Any,
            propertyName: String,
        ) {

            // Act & Assert:
            withClue("Failed for property: $propertyName.") {
                actualProperty shouldBe expectedProperty
            }
        }
    }
}