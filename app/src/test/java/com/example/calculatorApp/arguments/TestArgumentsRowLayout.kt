//package com.example.calculatorApp.arguments
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.RectangleShape
//import androidx.compose.ui.unit.dp
//import com.example.calculatorApp.model.layout.row.RowLayoutStandard
//import org.junit.jupiter.params.provider.Arguments
//import java.util.stream.Stream
//
//data class TestArgumentsRowLayout (
//    val standard: RowLayoutStandard = RowLayoutStandard(),
//) : TestArguments {
//
//    fun provideStandardProperties(): Stream<Arguments> {
//        return Stream.of(
//            Arguments.of(standard.alignment, Alignment.Center, "alignment"),
//            Arguments.of(standard.modifier, Modifier, "modifier"),
//            Arguments.of(standard.shape, RectangleShape, "shape"),
//            Arguments.of(standard.weight, 1f, "weight"),
//
//            Arguments.of(standard.alignmentVertical, Alignment.CenterVertically, "alignmentVertical"),
//            Arguments.of(standard.arrangementHorizontal, Arrangement.spacedBy(8.dp), "arrangementHorizontal"),
//            Arguments.of(standard.arrangementVertical, Arrangement.spacedBy(8.dp), "arrangementVertical"),
//
//            Arguments.of(standard.color, Color.Green, "color"),
//        )
//    }
//}