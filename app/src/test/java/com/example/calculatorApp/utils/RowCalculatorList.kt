//package com.example.calculatorApp.utils
//
//import com.example.calculatorApp.model.elements.button.ButtonData
//import com.example.calculatorApp.model.elements.row.RowCalculatorStandard
//import com.example.calculatorApp.utils.ElementsDataTest.buttonsBinaryTest
//import com.example.calculatorApp.utils.ElementsDataTest.buttonsControlsTest
//import com.example.calculatorApp.utils.ElementsDataTest.buttonsNumbersTest
//import com.example.calculatorApp.utils.RowCalculatorMappings.standardVisualsMapTest
//import com.example.calculatorApp.utils.TestUtils.provideSequenceConstructed
//
//object RowCalculatorList {
//
//    val buttonDataBinaryTest = buttonsBinaryTest.map { ButtonData(it) }
//    val buttonDataControlsTest = buttonsControlsTest.map { ButtonData(it) }
//    val buttonDataNumbersTest = buttonsNumbersTest.map { ButtonData(it) }
//
//    val standardRows = provideSequenceConstructed(
//        type = RowCalculatorStandard::class,
//        constructorArgs = standardVisualsMapTest,
//    )
//}