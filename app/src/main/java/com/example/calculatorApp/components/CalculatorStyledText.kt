package com.example.calculatorApp.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculatorApp.model.elements.display.DisplayCalculatorInput
import com.example.calculatorApp.model.elements.display.DisplayData
import com.example.calculatorApp.model.styles.StyleCalculator

@Composable
fun CalculatorStyledText(paramsStyledText: ParamsStyledText) {
    Text(
        text = paramsStyledText.paramsDisplayText.text,
        textAlign = paramsStyledText.paramsDisplayText.data.layout.alignText,
        color = paramsStyledText.paramsDisplayText.data.element.getForegroundColor(paramsStyledText.paramsDisplayText.style.displayStyle),
        modifier = paramsStyledText.paramsDisplayText.modifier.drawWithContent {
            if (paramsStyledText.paramsDisplayText.shouldDraw) drawContent()
        },
        softWrap = false,
        onTextLayout = { result: TextLayoutResult ->
            paramsStyledText.paramsTextStyle.onAdjustTextStyle(
                result,
                paramsStyledText.paramsDisplayText.resizedTextStyle,
                paramsStyledText.paramsDisplayText.data.layout.sizeFont,
                paramsStyledText.paramsDisplayText.data.layout.weightFont,
                paramsStyledText.paramsDisplayText.data.element.getForegroundColor(style = paramsStyledText.paramsDisplayText.style.displayStyle)
            )
        },
        style = paramsStyledText.paramsDisplayText.resizedTextStyle
    )

    LaunchedEffect(Unit) {
        paramsStyledText.paramsTextStyle.onSetInitialTextStyle(paramsStyledText.paramsDisplayText.textStyle)
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 100, name = "CalculatorStyledText")
@Composable
fun CalculatorStyledTextPreview() {

    val paramsDisplayText = ParamsDisplayText(
        text = "123.45",
        data = DisplayData(DisplayCalculatorInput.Standard),
        style = StyleCalculator.Standard,
        textStyle = TextStyle.Default,
        shouldDraw = true,
        resizedTextStyle = TextStyle.Default,
    )

    val paramsTextStyle = ParamsTextStyle(
        onSetInitialTextStyle = { /* No-op */ },
        onAdjustTextStyle = { _, _, _, _, _ -> /* No-op */ }
    )

    val paramsStyledText = ParamsStyledText(
        paramsDisplayText = paramsDisplayText,
        paramsTextStyle = paramsTextStyle,
    )

    CalculatorStyledText(paramsStyledText = paramsStyledText)
}