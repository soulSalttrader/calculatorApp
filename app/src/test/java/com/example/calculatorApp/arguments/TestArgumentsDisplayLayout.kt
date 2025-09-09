//package com.example.calculatorApp.arguments
//
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.RectangleShape
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.calculatorApp.model.layout.display.DisplayLayoutInput
//import org.junit.jupiter.params.provider.Arguments
//import java.util.stream.Stream
//
//class TestArgumentsDisplayLayout(
//    private val input: DisplayLayoutInput = DisplayLayoutInput(),
//) : TestArguments {
//
//    fun provideInputProperties(): Stream<Arguments> {
//        return Stream.of(
//            Arguments.of(input.alignment, Alignment.BottomEnd, "alignment"),
//            Arguments.of(input.modifier, Modifier.fillMaxWidth().padding(4.dp), "modifier"),
//            Arguments.of(input.shape, RectangleShape, "shape"),
//            Arguments.of(input.weight, 2f, "weight"),
//
//            Arguments.of(input.alignText, TextAlign.End, "textAlign"),
//            Arguments.of(input.sizeFont, 85.sp, "fontSize"),
//            Arguments.of(input.weightFont, FontWeight.Light, "fontWeight"),
//        )
//    }
//}