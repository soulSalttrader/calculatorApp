package com.example.calculatorApp.model

import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.model.elements.row.RowCalculatorStandard
import com.example.calculatorApp.model.elements.row.RowData
import com.example.calculatorApp.model.state.CalculatorStateDomain
import com.example.calculatorApp.model.styles.Style

sealed class ProviderRow : Provider<Sequence<RowData>> {

    data object StandardRows : ProviderRow() {
        override fun create(state: CalculatorStateDomain, style: Style): Sequence<RowData> {
            return ProviderRowConfigs.standardRows.map { row ->
                createRowData(row, state, style)
            }
        }
    }

    fun createRowData(row: RowCalculatorStandard, state: CalculatorStateDomain, style: Style): RowData {
        val dynamicButtons = row.buttons.map { button ->
            runCatching {
                ProviderButton.getDynamicButton(button.element, state, style)
            }.getOrElse { exception ->
                println("Error creating button ${button.element}: ${exception.message}")
                ButtonData(element = button.element)
            }
        }

        return RowData(element = row.withButtons(newButtons = dynamicButtons))
    }
}