package com.example.calculatorApp.model.elements.row

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.button.ButtonData

sealed class RowCalculatorStandard(override val buttons: Sequence<ButtonData>) : Row {

    abstract fun withButtons(newButtons: Sequence<ButtonData>): RowCalculatorStandard

    fun validateButtonCount(buttons: Sequence<ButtonData>, expectedCount: Int = 4) {
        require(buttons.count() == expectedCount) {
            "Expected $expectedCount buttons, but got ${buttons.count()}"
        }
    }

    data class Standard1(private val buttonsSeq: Sequence<ButtonData>) : RowCalculatorStandard(buttonsSeq) {
        override fun withButtons(newButtons: Sequence<ButtonData>): RowCalculatorStandard {
            validateButtonCount(newButtons)
            return Standard1(newButtons)
        }
        override val buttons: Sequence<ButtonData> = buttonsSeq
    }

    data class Standard2(private val buttonsSeq: Sequence<ButtonData>) : RowCalculatorStandard(buttonsSeq) {
        override fun withButtons(newButtons: Sequence<ButtonData>): RowCalculatorStandard {
            validateButtonCount(newButtons)
            return Standard2(newButtons)
        }
        override val buttons: Sequence<ButtonData> = buttonsSeq
    }

    data class Standard3(private val buttonsSeq: Sequence<ButtonData>) : RowCalculatorStandard(buttonsSeq) {
        override fun withButtons(newButtons: Sequence<ButtonData>): RowCalculatorStandard {
            validateButtonCount(newButtons)
            return Standard3(newButtons)
        }
        override val buttons: Sequence<ButtonData> = buttonsSeq
    }

    data class Standard4(private val buttonsSeq: Sequence<ButtonData>) : RowCalculatorStandard(buttonsSeq) {
        override fun withButtons(newButtons: Sequence<ButtonData>): RowCalculatorStandard {
            validateButtonCount(newButtons)
            return Standard4(newButtons)
        }
        override val buttons: Sequence<ButtonData> = buttonsSeq
    }

    data class Standard5(private val buttonsSeq: Sequence<ButtonData>) : RowCalculatorStandard(buttonsSeq) {
        override fun withButtons(newButtons: Sequence<ButtonData>): RowCalculatorStandard {
            validateButtonCount(newButtons, 3)
            return Standard5(newButtons)
        }
        override val buttons: Sequence<ButtonData> = buttonsSeq
    }

    override fun getCategory(): ElementCategory<ElementColorStyle> = RowCategory.Standard
    override fun getBackgroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).backgroundColor
    override fun getForegroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).foregroundColor

    override fun getStyle(style: ElementCategoryStyleCollection<ElementColorStyle>): ElementColorStyle {
        val categoryStyle = style.categories[getCategory()]
            ?: throw IllegalArgumentException("Category '${getCategory()}' not found.")

        return categoryStyle.specificStyles[this::class.simpleName] ?: categoryStyle.baseStyle
    }
}