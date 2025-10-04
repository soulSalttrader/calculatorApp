package com.example.calculatorApp.model.elements.row

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.button.ButtonData

sealed class RowCalculatorStandard(override val buttons: Sequence<ButtonData>) : Row {

    class Standard1(override val buttons: Sequence<ButtonData>) : RowCalculatorStandard(buttons)
    class Standard2(override val buttons: Sequence<ButtonData>) : RowCalculatorStandard(buttons)
    class Standard3(override val buttons: Sequence<ButtonData>) : RowCalculatorStandard(buttons)
    class Standard4(override val buttons: Sequence<ButtonData>) : RowCalculatorStandard(buttons)
    class Standard5(override val buttons: Sequence<ButtonData>) : RowCalculatorStandard(buttons)

    override fun getCategory(): ElementCategory<ElementColorStyle> = RowCategory.Standard
    override fun getBackgroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).backgroundColor
    override fun getForegroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).foregroundColor

    override fun getStyle(style: ElementCategoryStyleCollection<ElementColorStyle>): ElementColorStyle {
        val categoryStyle = style.categories[getCategory()]
            ?: throw IllegalArgumentException("Category '${getCategory()}' not found.")

        return categoryStyle.specificStyles[this::class.simpleName] ?: categoryStyle.baseStyle
    }
}