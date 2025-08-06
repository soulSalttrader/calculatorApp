//package com.example.calculatorApp.utils
//
//import com.example.calculatorApp.annotations.ConceptClass
//import com.example.calculatorApp.model.elements.display.Display
//import com.example.calculatorApp.model.elements.display.DisplayCalculatorInput
//import com.example.calculatorApp.ui.theme.Black
//import com.example.calculatorApp.ui.theme.White
//import kotlin.reflect.KClass
//
//object DisplayCalculatorMappings {
//
//    @OptIn(ConceptClass::class)
//    val inputsVisualsMapTest: Map<KClass<out Display>, Visuals> = mapOf(
//        DisplayCalculatorInput.Standard::class to VisualsDisplay(
//            background = Black,
//            foreground = White,
//        ),
//        DisplayCalculatorInput.Scientific::class to VisualsDisplay(
//            background = Black,
//            foreground = White,
//        ),
//    )
//}