//package com.example.calculatorApp.testData
//
//import com.example.calculatorApp.annotations.ConceptClass
//import com.example.calculatorApp.model.elements.button.Button
//import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
//import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
//import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
//import com.example.calculatorApp.model.elements.button.ButtonCalculatorParenthesis
//import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
//import com.example.calculatorApp.model.elements.button.ButtonCategory
//import com.example.calculatorApp.model.elements.display.Display
//import com.example.calculatorApp.model.elements.display.DisplayCalculatorInput
//import com.example.calculatorApp.model.elements.row.Row
//import com.example.calculatorApp.model.elements.row.RowCalculatorStandard
//import com.example.calculatorApp.model.symbols.SymbolButton
//import com.example.calculatorApp.testData.expected.Expected
//import com.example.calculatorApp.testData.expected.ExpectedElement
//import com.example.calculatorApp.ui.theme.Black
//import com.example.calculatorApp.ui.theme.Onyx
//import com.example.calculatorApp.ui.theme.SilverGrey
//import com.example.calculatorApp.ui.theme.VividGamboge
//import com.example.calculatorApp.ui.theme.White
//import kotlin.reflect.KClass
//
//object TestDataElementExpectedMap {
//
//    val binaryExpectedMapTest: Map<KClass<out Button>, Expected> = mapOf(
//        ButtonCalculatorBinary.Addition::class to ExpectedElement.Button(
//            background = VividGamboge,
//            foreground = White,
//            category = ButtonCategory.Binary,
//            symbol = SymbolButton.ADDITION,
//        ),
//        ButtonCalculatorBinary.Subtraction::class to ExpectedElement.Button(
//            background = VividGamboge,
//            foreground = White,
//            category = ButtonCategory.Binary,
//            symbol = SymbolButton.SUBTRACTION,
//        ),
//        ButtonCalculatorBinary.Multiplication::class to ExpectedElement.Button(
//            background = VividGamboge,
//            foreground = White,
//            category = ButtonCategory.Binary,
//            symbol = SymbolButton.MULTIPLICATION,
//        ),
//        ButtonCalculatorBinary.Division::class to ExpectedElement.Button(
//            background = VividGamboge,
//            foreground = White,
//            category = ButtonCategory.Binary,
//            symbol = SymbolButton.DIVISION,
//        ),
//    )
//
//    val unaryExpectedMapTest: Map<KClass<out Button>, Expected> = mapOf(
//        ButtonCalculatorUnary.Sign::class to ExpectedElement.Button(
//            background = SilverGrey,
//            foreground = Onyx,
//            category = ButtonCategory.Unary,
//            symbol = SymbolButton.SIGN,
//            isPrefix = true,
//        ),
//        ButtonCalculatorUnary.Percentage::class to ExpectedElement.Button(
//            background = SilverGrey,
//            foreground = Onyx,
//            category = ButtonCategory.Unary,
//            symbol = SymbolButton.PERCENTAGE,
//            isPrefix = false,
//        ),
//    )
//
//    val controlExpectedMapTest: Map<KClass<out Button>, Expected> = mapOf(
//        ButtonCalculatorControl.AllClear::class to ExpectedElement.Button(
//            background = SilverGrey,
//            foreground = Onyx,
//            category = ButtonCategory.Control,
//            symbol = SymbolButton.ALL_CLEAR,
//        ),
//        ButtonCalculatorControl.Clear::class to ExpectedElement.Button(
//            background = SilverGrey,
//            foreground = Onyx,
//            category = ButtonCategory.Control,
//            symbol = SymbolButton.CLEAR,
//        ),
//        ButtonCalculatorControl.Decimal::class to ExpectedElement.Button(
//            background = Onyx,
//            foreground = White,
//            category = ButtonCategory.Control,
//            symbol = SymbolButton.DECIMAL,
//        ),
//        ButtonCalculatorControl.Equals::class to ExpectedElement.Button(
//            background = VividGamboge,
//            foreground = White,
//            category = ButtonCategory.Control,
//            symbol = SymbolButton.EQUALS,
//        ),
//    )
//
//    val numberExpectedMapTest: Map<KClass<out Button>, Expected> = mapOf(
//        ButtonCalculatorNumber.Zero::class to ExpectedElement.Button(
//            background = Onyx,
//            foreground = White,
//            category = ButtonCategory.Number,
//            symbol = SymbolButton.ZERO,
//        ),
//        ButtonCalculatorNumber.One::class to ExpectedElement.Button(
//            background = Onyx,
//            foreground = White,
//            category = ButtonCategory.Number,
//            symbol = SymbolButton.ONE,
//        ),
//        ButtonCalculatorNumber.Two::class to ExpectedElement.Button(
//            background = Onyx,
//            foreground = White,
//            category = ButtonCategory.Number,
//            symbol = SymbolButton.TWO,
//        ),
//        ButtonCalculatorNumber.Three::class to ExpectedElement.Button(
//            background = Onyx,
//            foreground = White,
//            category = ButtonCategory.Number,
//            symbol = SymbolButton.THREE,
//        ),
//        ButtonCalculatorNumber.Four::class to ExpectedElement.Button(
//            background = Onyx,
//            foreground = White,
//            category = ButtonCategory.Number,
//            symbol = SymbolButton.FOUR,
//        ),
//        ButtonCalculatorNumber.Five::class to ExpectedElement.Button(
//            background = Onyx,
//            foreground = White,
//            category = ButtonCategory.Number,
//            symbol = SymbolButton.FIVE,
//        ),
//        ButtonCalculatorNumber.Six::class to ExpectedElement.Button(
//            background = Onyx,
//            foreground = White,
//            category = ButtonCategory.Number,
//            symbol = SymbolButton.SIX,
//        ),
//        ButtonCalculatorNumber.Seven::class to ExpectedElement.Button(
//            background = Onyx,
//            foreground = White,
//            category = ButtonCategory.Number,
//            symbol = SymbolButton.SEVEN,
//        ),
//        ButtonCalculatorNumber.Eight::class to ExpectedElement.Button(
//            background = Onyx,
//            foreground = White,
//            category = ButtonCategory.Number,
//            symbol = SymbolButton.EIGHT,
//        ),
//        ButtonCalculatorNumber.Nine::class to ExpectedElement.Button(
//            background = Onyx,
//            foreground = White,
//            category = ButtonCategory.Number,
//            symbol = SymbolButton.NINE,
//        ),
//    )
//
//    val parenthesisExpectedMap: Map<KClass<out Button>, Expected> = mapOf(
//        ButtonCalculatorParenthesis.OpenParenthesis::class to ExpectedElement.Button(
//            background = Onyx,
//            foreground = White,
//            category = ButtonCategory.Parenthesis,
//            symbol = SymbolButton.OPEN_PARENT,
//        ),
//        ButtonCalculatorParenthesis.CloseParenthesis::class to ExpectedElement.Button(
//            background = Onyx,
//            foreground = White,
//            category = ButtonCategory.Parenthesis,
//            symbol = SymbolButton.CLOSE_PARENT,
//        ),
//    )
//
//    @OptIn(ConceptClass::class)
//    val inputsExpectedMapTest: Map<KClass<out Display>, Expected> = mapOf(
//        DisplayCalculatorInput.Standard::class to ExpectedElement.Display(
//            background = Black,
//            foreground = White,
//        ),
//        DisplayCalculatorInput.Scientific::class to ExpectedElement.Display(
//            background = Black,
//            foreground = White,
//        ),
//    )
//
//    val standardExpectedMapTest: Map<KClass<out Row>, Expected> = mapOf(
//        RowCalculatorStandard.Standard1::class to ExpectedElement.Row(
//            background = Black,
//            foreground = White,
//            buttonData = TestDataElementSeq.buttonDataBinaryTest,
//        ),
//        RowCalculatorStandard.Standard2::class to ExpectedElement.Row(
//            background = Black,
//            foreground = White,
//            buttonData = TestDataElementSeq.buttonDataNumbersTest,
//        ),
//        RowCalculatorStandard.Standard3::class to ExpectedElement.Row(
//            background = Black,
//            foreground = White,
//            buttonData = TestDataElementSeq.buttonDataBinaryTest,
//        ),
//        RowCalculatorStandard.Standard4::class to ExpectedElement.Row(
//            background = Black,
//            foreground = White,
//            buttonData = TestDataElementSeq.buttonDataNumbersTest,
//        ),
//        RowCalculatorStandard.Standard5::class to ExpectedElement.Row(
//            background = Black,
//            foreground = White,
//            buttonData = TestDataElementSeq.buttonDataControlsTest,
//        ),
//    )
//}