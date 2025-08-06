//package com.example.calculatorApp.arguments
//
//import com.example.calculatorApp.model.elements.row.RowCalculatorStandard
//import com.example.calculatorApp.utils.RowCalculatorList
//import com.example.calculatorApp.utils.RowCalculatorMappings
//import com.example.calculatorApp.utils.TestUtils.mapSingletonsTo
//
////object TestArgumentsRow : TestArguments {
////
////    fun provideRowColors() = mapSingletonsTo(
////        type = RowCalculatorStandard::class,
////        items = RowCalculatorList.standardRows,
////        mapping = { row -> RowCalculatorMappings.standardColorMap[row::class] }
////    )
////
////    fun provideStandardButtons() = mapSingletonsTo(
////        type = RowCalculatorStandard::class,
////        items = RowCalculatorList.standardRows,
////        mapping = { row -> RowCalculatorMappings.standardButtonsMap[row::class] }
////    )
////}