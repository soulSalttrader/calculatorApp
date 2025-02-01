package com.example.calculatorApp.view.framework

import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.maps.shouldBeEmpty
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ElementCategoryStyleCollectionImplTest {

    @Nested
    inner class GetCategories {

        @Test
        fun `ElementCategoryStyleCollectionImpl should store categories correctly`() {
            // Arrange:
            val categoryMock = mockk<ElementCategory<ElementColorStyle>>(relaxed = true)
            val styleMock = mockk<ElementCategoryStyle<ElementColorStyle>>(relaxed = true)

            val categories: MutableMap<ElementCategory<ElementColorStyle>, ElementCategoryStyle<ElementColorStyle>> =
                mutableMapOf(categoryMock to styleMock)

            // Act:
            val collection = ElementCategoryStyleCollectionImpl(categories)

            // Assert:
            1 shouldBeEqual collection.categories.size
            styleMock shouldBe collection.categories[categoryMock]
        }

        @Test
        fun `ElementCategoryStyleCollectionImpl should handle empty categories map`() {
            // Arrange
            val categories = mutableMapOf<ElementCategory<ElementColorStyle>, ElementCategoryStyle<ElementColorStyle>>()

            // Act
            val collection = ElementCategoryStyleCollectionImpl(categories)

            // Assert
            collection.categories.shouldBeEmpty()
        }
    }
}