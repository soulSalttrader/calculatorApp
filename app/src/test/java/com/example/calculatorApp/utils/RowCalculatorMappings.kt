package com.example.calculatorApp.utils

import com.example.calculatorApp.model.elements.row.Row
import com.example.calculatorApp.model.elements.row.RowCalculatorStandard
import com.example.calculatorApp.ui.theme.Black
import com.example.calculatorApp.ui.theme.White
import com.example.calculatorApp.utils.RowCalculatorList.buttonsBinaryTest
import com.example.calculatorApp.utils.RowCalculatorList.buttonsControlsTest
import com.example.calculatorApp.utils.RowCalculatorList.buttonsNumbersTest
import kotlin.reflect.KClass

object RowCalculatorMappings {

    val standardVisualsMap: Map<KClass<out Row>, Visuals> = mapOf(
        RowCalculatorStandard.Standard1::class to VisualsRow(
                background = Black,
                foreground = White,
                buttonData = buttonsBinaryTest,
        ),
        RowCalculatorStandard.Standard2::class to VisualsRow(
                background = Black,
                foreground = White,
                buttonData = buttonsNumbersTest,
        ),
        RowCalculatorStandard.Standard3::class to VisualsRow(
                background = Black,
                foreground = White,
                buttonData = buttonsBinaryTest,
        ),
        RowCalculatorStandard.Standard4::class to VisualsRow(
                background = Black,
                foreground = White,
                buttonData = buttonsNumbersTest,
        ),
        RowCalculatorStandard.Standard5::class to VisualsRow(
                background = Black,
                foreground = White,
                buttonData = buttonsControlsTest,
        ),
    )
}