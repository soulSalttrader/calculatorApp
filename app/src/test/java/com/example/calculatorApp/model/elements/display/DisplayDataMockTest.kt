package com.example.calculatorApp.model.elements.display

import com.example.calculatorApp.model.layout.ElementLayout
import com.example.calculatorApp.model.layout.display.DisplayLayoutInput
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.mockk
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DisplayDataMockTest {

    @Nested
    inner class GetElement {

        @Test
        fun `should return the correct Display instance`() {
            val display = mockk<Display>()
            val layout = mockk<ElementLayout>()
            val displayData = DisplayData(element = display, layout = layout)

            display shouldBe  displayData.element
        }
    }

    @Nested
    inner class GetLayout {

        @Test
        fun `should return the correct layout instance`() {
            val display = mockk<Display>()
            val layout = mockk<ElementLayout>()
            val displayData = DisplayData(element = display, layout = layout)

            layout shouldBe displayData.layout
        }

        @Test
        fun `should return the correct DisplayLayoutInput instance by default`() {
            val display = mockk<Display>()
            val displayData = DisplayData(element = display)

            displayData.layout.shouldBeInstanceOf<DisplayLayoutInput>()
        }
    }

    @Nested
    inner class GetPlaceholderText {

        @Test
        fun `should return expected text - Mocked`() {
            val display = mockk<Display>()
            val layout = mockk<ElementLayout>()
            val data = DisplayData(element = display, layout = layout)

            data.getPlaceholderText() shouldBe "329 329.329"
        }
    }
}