package com.example.calculatorApp.model.elements

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.Element
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ElementTest {

    @Nested
    inner class GetColor {

        @Test
        fun `Element should return correct background and text color`() {
            // Arrange:
            val mockCategory = mockk<ElementCategory<ElementColorStyle>>(relaxed = true)
            val mockStyle = mockk<ElementCategoryStyleCollection<ElementColorStyle>>(relaxed = true)
            val mockElement = mockk<
                    Element<
                            ElementCategory<ElementColorStyle>,
                            ElementCategoryStyleCollection<ElementColorStyle>,
                            ElementColorStyle>
                    >(relaxed = true)

            every { mockElement.getBackgroundColor(mockStyle) } returns Color.Red
            every { mockElement.getTextColor(mockStyle) } returns Color.White
            every { mockElement.getCategory() } returns mockCategory

            // Act:
            val backgroundColor = mockElement.getBackgroundColor(mockStyle)
            val textColor = mockElement.getTextColor(mockStyle)

            // Assert:
            Color.Red shouldBe backgroundColor
            Color.White shouldBe textColor
        }
    }

    @Nested
    inner class GetCategory {

        @Test
        fun `Element should return correct category`() {
            // Arrange:
            val expectedCategory = mockk<ElementCategory<ElementColorStyle>>()
            val mockElement = mockk<
                    Element<
                            ElementCategory<ElementColorStyle>,
                            ElementCategoryStyleCollection<ElementColorStyle>,
                            ElementColorStyle>
                    >(relaxed = true)

            every { mockElement.getCategory() } returns expectedCategory

            // Act:
            val category = mockElement.getCategory()

            // Assert:
            expectedCategory shouldBe category
        }
    }
}