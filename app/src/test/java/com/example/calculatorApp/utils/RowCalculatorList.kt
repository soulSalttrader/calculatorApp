package com.example.calculatorApp.utils

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.model.elements.row.Row
import com.example.calculatorApp.model.elements.row.RowCalculatorStandard
import com.example.calculatorApp.utils.ButtonCalculatorList.binary
import com.example.calculatorApp.utils.ButtonCalculatorList.controls
import com.example.calculatorApp.utils.ButtonCalculatorList.numbers
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

object RowCalculatorList {
    val buttonsBinaryTest = binary.map { ButtonData(it) }
    val buttonsControlsTest = controls.map { ButtonData(it) }
    val buttonsNumbersTest = numbers.map { ButtonData(it) }

    private fun provideSequenceRows(rowClass: KClass<out Row>): Sequence<Row> {
        return try {
            rowClass.sealedSubclasses
                .asSequence()
                .mapIndexedNotNull { index, subclass ->
                    val buttons = if (index % 2 == 0) buttonsBinaryTest else buttonsNumbersTest
                    runCatching { subclass.constructors.first().call(buttons) }.getOrNull()
                }
        } catch (e: Exception) {
            throw IllegalArgumentException("Unknown button class: $rowClass.")
        }
    }

    val standards = provideSequenceRows(RowCalculatorStandard::class)

//    val allRows: List<Row> = standards.toList()
    val allRows: Sequence<Row> = standards
}