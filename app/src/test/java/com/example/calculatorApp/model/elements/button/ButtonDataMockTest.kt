package com.example.calculatorApp.model.elements.button

import com.example.calculatorApp.model.layout.button.ButtonLayout
import com.example.calculatorApp.model.layout.button.ButtonLayoutRegular
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.mockk
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ButtonDataMockTest {

    @Nested
    inner class GetElement {

        @Test
        fun `should return the correct Button instance`() {
            val button = mockk<Button>()
            val layout = mockk<ButtonLayout>()
            val displayData = ButtonData(element = button, layout = layout)

            button shouldBe displayData.element
        }
    }

    @Nested
    inner class GetLayout {

        @Test
        fun `getLayout should return the correct layout instance`() {
            val button = mockk<Button>()
            val layout = mockk<ButtonLayout>()
            val displayData = ButtonData(element = button, layout = layout)

            layout shouldBe displayData.layout
        }

        @Test
        fun `getLayout should return the correct DisplayLayoutInput instance by default`() {
            val button = mockk<Button>()
            val displayData = ButtonData(element = button)

            displayData.layout.shouldBeInstanceOf<ButtonLayoutRegular>()
        }
    }
}