package com.example.calculatorApp.utils

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.model.elements.row.RowCalculatorStandard
import com.example.calculatorApp.ui.theme.Black
import kotlin.reflect.KClass

object RowCalculatorMappings {

    val standardColorMap: Map<KClass<out RowCalculatorStandard>, Color> = mapOf(
        RowCalculatorStandard.Standard1::class to Black,
        RowCalculatorStandard.Standard2::class to Black,
        RowCalculatorStandard.Standard3::class to Black,
        RowCalculatorStandard.Standard4::class to Black,
        RowCalculatorStandard.Standard5::class to Black
    )

    // Even index (0, 2, 4) use buttonsArithmeticsTest
    // Odd index (1, 3) use buttonsNumbersTest
    val standardButtonsMap: Map<KClass<out RowCalculatorStandard>, Sequence<ButtonData>> = mapOf(
        RowCalculatorStandard.Standard1::class to RowCalculatorList.buttonsBinaryTest,
        RowCalculatorStandard.Standard2::class to RowCalculatorList.buttonsNumbersTest,
        RowCalculatorStandard.Standard3::class to RowCalculatorList.buttonsBinaryTest,
        RowCalculatorStandard.Standard4::class to RowCalculatorList.buttonsNumbersTest,
        RowCalculatorStandard.Standard5::class to RowCalculatorList.buttonsBinaryTest,
    )
}