//package com.example.calculatorApp.utils
//
//import com.example.calculatorApp.model.elements.row.Row
//import com.example.calculatorApp.model.elements.row.RowCalculatorStandard
//import com.example.calculatorApp.ui.theme.Black
//import com.example.calculatorApp.ui.theme.White
//import com.example.calculatorApp.utils.RowCalculatorList.buttonDataBinaryTest
//import com.example.calculatorApp.utils.RowCalculatorList.buttonDataControlsTest
//import com.example.calculatorApp.utils.RowCalculatorList.buttonDataNumbersTest
//import kotlin.reflect.KClass
//
//object RowCalculatorMappings {
//
//    val standardVisualsMapTest: Map<KClass<out Row>, Visuals> = mapOf(
//        RowCalculatorStandard.Standard1::class to VisualsRow(
//                background = Black,
//                foreground = White,
//                buttonData = buttonDataBinaryTest,
//        ),
//        RowCalculatorStandard.Standard2::class to VisualsRow(
//                background = Black,
//                foreground = White,
//                buttonData = buttonDataNumbersTest,
//        ),
//        RowCalculatorStandard.Standard3::class to VisualsRow(
//                background = Black,
//                foreground = White,
//                buttonData = buttonDataBinaryTest,
//        ),
//        RowCalculatorStandard.Standard4::class to VisualsRow(
//                background = Black,
//                foreground = White,
//                buttonData = buttonDataNumbersTest,
//        ),
//        RowCalculatorStandard.Standard5::class to VisualsRow(
//                background = Black,
//                foreground = White,
//                buttonData = buttonDataControlsTest,
//        ),
//    )
//}