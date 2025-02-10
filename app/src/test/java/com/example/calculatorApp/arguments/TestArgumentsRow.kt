package com.example.calculatorApp.arguments

import androidx.compose.ui.graphics.Color.Companion.Green
import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.utils.RowCalculatorList
import com.example.calculatorApp.utils.RowCalculatorMappings
import org.junit.jupiter.params.provider.Arguments

object TestArgumentsRow : TestArguments {

    private fun <T> provideRowsColors(rows: Array<T>, colorMapping: (T) -> Any) =
        rows.map { row -> Arguments.of(row, colorMapping(row)) }.stream()

    private fun <T> provideRowButtons(buttons: Array<T>, buttonMapping: (T) -> List<ButtonData>?) =
        buttons.map { button -> Arguments.of(button, buttonMapping(button)) }.stream()

    fun provideRowColors() = provideRowsColors(
        rows = RowCalculatorList.standards,
        colorMapping = { row -> RowCalculatorMappings.standardColorMap[row::class] ?: Green }
    )

    fun provideStandardButtons() = provideRowButtons(
        buttons = RowCalculatorList.standards,
        buttonMapping = { row -> RowCalculatorMappings.standardButtonsMap[row::class] ?: RowCalculatorList.buttonsControlsTest }
    )
}