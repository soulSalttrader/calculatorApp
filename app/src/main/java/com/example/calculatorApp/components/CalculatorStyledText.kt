package com.example.calculatorApp.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.calculatorApp.model.elements.display.DisplayCalculatorInput
import com.example.calculatorApp.model.elements.display.DisplayData
import com.example.calculatorApp.model.styles.Style
import com.example.calculatorApp.model.styles.StyleCalculator
import com.example.calculatorApp.presentation.CalculatorViewModel

@Composable
fun CalculatorStyledTextStateful(
    text: String,
    data: DisplayData,
    style: Style,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel,
) {
    val stateUi by viewModel.stateUi.collectAsStateWithLifecycle()

    CalculatorStyledText(
        text = text,
        data = data,
        style = style,
        textStyle = textStyle,
        modifier = modifier,
        shouldDraw = stateUi.shouldDraw,
        resizedTextStyle = stateUi.asTextStyle(),
        onSetInitialTextStyle = { style -> viewModel.setInitialTextStyle(style) },
        onAdjustTextStyle = { result, currentStyle, fallbackFontSize, fontWeight, color ->
            viewModel.adjustTextStyleIfNeeded(result, currentStyle, fallbackFontSize, fontWeight, color)
        }
    )
}

@Composable
fun CalculatorStyledText(
    text: String,
    data: DisplayData,
    style: Style,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    shouldDraw: Boolean,
    resizedTextStyle: TextStyle,
    onSetInitialTextStyle: (TextStyle) -> Unit,
    onAdjustTextStyle: (TextLayoutResult, TextStyle, TextUnit, FontWeight, Color) -> Unit
) {
    Text(
        text = text,
        textAlign = data.layout.alignText,
        color = data.element.getForegroundColor(style.displayStyle),
        modifier = modifier.drawWithContent {
            if (shouldDraw) drawContent()
        },
        softWrap = false,
        onTextLayout = { result: TextLayoutResult ->
            onAdjustTextStyle(
                result,
                resizedTextStyle,
                data.layout.sizeFont,
                data.layout.weightFont,
                data.element.getForegroundColor(style = style.displayStyle)
            )
        },
        style = resizedTextStyle
    )

    LaunchedEffect(Unit) {
        onSetInitialTextStyle(textStyle)
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 100, name = "CalculatorStyledText")
@Composable
fun CalculatorStyledTextPreview() {
    val displayData = DisplayData(DisplayCalculatorInput.Standard)

    CalculatorStyledText(
        text = "123.45",
        data = displayData,
        style = StyleCalculator.Standard,
        textStyle = TextStyle.Default,
        shouldDraw = true,
        resizedTextStyle = TextStyle.Default,
        onSetInitialTextStyle = { TextStyle.Default },
        onAdjustTextStyle = { _, _, _, _, _ -> /* No-op */ }
    )
}