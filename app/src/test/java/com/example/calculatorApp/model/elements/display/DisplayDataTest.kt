package com.example.calculatorApp.model.elements.display

import com.example.calculatorApp.annotations.ConceptMethod
import com.example.calculatorApp.model.layout.display.DisplayLayoutInput
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DisplayDataTest {

    @Nested
    inner class GetPlaceholderText {

        @Test
        fun `should return expected text`() {
            val element = DisplayCalculatorInput.Standard
            val layout = DisplayLayoutInput()

            val data = DisplayData(element = element, layout = layout)
            "329 329.329" shouldBe data.getPlaceholderText()
        }
    }

    @Nested
    inner class DisplayText {

        @Test
        @OptIn(ConceptMethod::class)
        fun `should throw NotImplementedError`() {
            val element = DisplayCalculatorInput.Standard
            val layout = DisplayLayoutInput()

            val data = DisplayData(element = element, layout = layout)

            shouldThrow<NotImplementedError> {
                data.displayText()
            }
        }
    }
}