package com.example.calculatorApp.model.layout.button

import com.example.calculatorApp.arguments.TestArgumentsButtonLayout
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ButtonLayoutWideTest {

    @Nested
    inner class ButtonLayoutWidePropertiesTest {

        // Arrange:
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsButtonLayout().provideWideProperties()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should return the correct property for button layout`(
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