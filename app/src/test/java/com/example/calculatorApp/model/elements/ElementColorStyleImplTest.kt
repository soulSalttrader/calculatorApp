package com.example.calculatorApp.model.elements

import androidx.compose.ui.graphics.Color
import io.kotest.matchers.equals.shouldBeEqual
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ElementColorStyleImplTest {

    @Test
    fun `should call getBackgroundColor and getTextColor`() {
        // Arrange:
        val mockCategory = mockk<ElementCategory<ElementColorStyle>>(relaxed = true)
        val mockStyle = mockk<ElementCategoryStyleCollection<ElementColorStyle>>(relaxed = true)
        val mockElement = mockk<Element<ElementCategory<ElementColorStyle>, ElementCategoryStyleCollection<ElementColorStyle>, ElementColorStyle>>(relaxed = true)

        every { mockElement.getBackgroundColor(mockStyle) } returns Color.Red
        every { mockElement.getTextColor(mockStyle) } returns Color.White
        every { mockElement.getCategory() } returns mockCategory

        // Act:
        mockElement.getBackgroundColor(mockStyle)
        mockElement.getTextColor(mockStyle)

        // Assert:
        verify { mockElement.getBackgroundColor(mockStyle) }
        verify { mockElement.getTextColor(mockStyle) }
    }

    @Nested
    inner class GetBackgroundColor {

        @Test
        fun `should return correct background color`() {
            // Arrange:
            val colorStyle = ElementColorStyleImpl(Color.Red, Color.Black)

            // Act: (Implicit) Retrieve the backgroundColor property

            // Assert:
            Color.Red shouldBeEqual colorStyle.backgroundColor
        }
    }

    @Nested
    inner class GetTextColor {

        @Test
        fun `ElementColorStyleImpl should return correct text color`() {
            // Arrange:
            val colorStyle = ElementColorStyleImpl(Color.Red, Color.Black)

            // Act: (Implicit) Retrieve the textColor property

            // Assert:
            Color.Black shouldBeEqual colorStyle.textColor
        }
    }
}