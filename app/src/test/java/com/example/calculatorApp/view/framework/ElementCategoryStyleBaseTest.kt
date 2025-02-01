package com.example.calculatorApp.view.framework

import androidx.compose.ui.graphics.Color
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.maps.shouldBeEmpty
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ElementCategoryStyleBaseTest {

    @Nested
    inner class GetSpecificStyle {
        @Test
        fun `getSpecificStyle should default to empty map if specificStyles is null`() {
            // Arrange:
            val baseStyle = ElementColorStyleImpl(Color.Green, Color.Black)

            // Act:
            val categoryStyle = object : ElementCategoryStyleBase<ElementColorStyle>(baseStyle, null) {}

            // Assert:
            categoryStyle.specificStyles.shouldBeEmpty()
        }
    }

    @Nested
    inner class GetBaseStyle {
        @Test
        fun `getBaseStyle should store base style and specific styles`() {
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