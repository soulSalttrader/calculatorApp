package com.example.calculatorApp.model.elements

import androidx.compose.ui.graphics.Color
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.maps.shouldBeEmpty
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ElementCategoryStyleBaseTest {

    @Nested
    inner class SpecificStyle {
        @Test
        fun `should default to empty map if specificStyles is null`() {
            // Arrange:
            val baseStyle = ElementColorStyleImpl(Color.Green, Color.Black)

            // Act:
            val categoryStyle = object : ElementCategoryStyleBase<ElementColorStyle>(baseStyle, null) {}

            // Assert:
            categoryStyle.specificStyles.shouldBeEmpty()
        }
    }

    @Nested
    inner class BaseStyle {
        @Test
        fun `should store base style and specific styles`() {
            // Arrange:
            val baseStyle = ElementColorStyleImpl(Color.Blue, Color.Yellow)
            val specificStyles = mapOf("test" to ElementColorStyleImpl(Color.Red, Color.White))

            // Act:
            val categoryStyle = object : ElementCategoryStyleBase<ElementColorStyle>(baseStyle, specificStyles) {}

            // Assert:
            baseStyle shouldBeEqual categoryStyle.baseStyle
            1 shouldBeEqual categoryStyle.specificStyles.size
            Color.Red shouldBe categoryStyle.specificStyles["test"]?.backgroundColor
        }
    }
}