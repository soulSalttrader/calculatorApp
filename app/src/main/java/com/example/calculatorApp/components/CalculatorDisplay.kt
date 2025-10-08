package com.example.calculatorApp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.calculatorApp.model.elements.display.DisplayCalculatorInput
import com.example.calculatorApp.model.elements.display.DisplayData
import com.example.calculatorApp.model.styles.Style
import com.example.calculatorApp.model.styles.StyleCalculator
import com.example.calculatorApp.presentation.CalculatorViewModel
import com.example.calculatorApp.ui.theme.VividGamboge
import com.example.calculatorApp.utils.Constants.SIZE_FONT_DEFAULT

@Composable
fun CalculatorDisplayStateful(
    modifier: Modifier = Modifier,
    text: String,
    data: DisplayData,
    style: Style,
    viewModel: CalculatorViewModel,
) {

    val stateUi by viewModel.stateUi.collectAsStateWithLifecycle()
    val horizontalOffset = stateUi.buttonWidth.dp

    CalculatorStyledBox(
        modifier = Modifier
            .then(modifier),
        layout = data.layout,
        backgroundColor = data.element.getBackgroundColor(style.displayStyle),
    ) {

        Text(
            text = data.getPlaceholderText(),
            textAlign = data.layout.alignText,
            modifier = data.layout.modifier
                .padding(horizontal = horizontalOffset),
            fontWeight = data.layout.weightFont,
            fontSize = data.layout.sizeFont,
            color = Color.Transparent,
        )

        CalculatorStyledText(
            text = text,
            data = data,
            style = style,
            textStyle = TextStyle(
                fontSize = data.layout.sizeFont,
                fontWeight = data.layout.weightFont
            ),
            modifier = data.layout.modifier
                .padding(horizontal = horizontalOffset),
            shouldDraw = stateUi.shouldDraw,
            resizedTextStyle = stateUi.asTextStyle(),
            onSetInitialTextStyle = { textStyle -> viewModel.setInitialTextStyle(textStyle) },
            onAdjustTextStyle = { result, currentStyle, fallbackFontSize, fontWeight, color ->
                viewModel.adjustTextStyleIfNeeded(result, currentStyle, fallbackFontSize, fontWeight, color)
            }
        )
    }
}

@Composable
fun CalculatorDisplay(
    modifier: Modifier = Modifier,
    text: String,
    data: DisplayData,
    style: Style,
    buttonWidth: Dp = 0.dp,
    textStyle: TextStyle,
    shouldDraw: Boolean = true,
    resizedTextStyle: TextStyle = TextStyle.Default,
    onSetInitialTextStyle: (TextStyle) -> Unit,
    onAdjustTextStyle: (TextLayoutResult, TextStyle, TextUnit, FontWeight, Color) -> Unit = { _, _, _, _, _ -> }
) {
    val horizontalOffset = buttonWidth

    CalculatorStyledBox(
        modifier = modifier,
        layout = data.layout,
        backgroundColor = data.element.getBackgroundColor(style.displayStyle)
    ) {
        Text(
            text = data.getPlaceholderText(),
            textAlign = data.layout.alignText,
            modifier = data.layout.modifier.padding(horizontal = horizontalOffset),
            fontWeight = data.layout.weightFont,
            fontSize = data.layout.sizeFont,
            color = Color.Transparent
        )

        CalculatorStyledText(
            text = text,
            data = data,
            style = style,
            textStyle = textStyle,
            modifier = data.layout.modifier.padding(horizontal = horizontalOffset),
            shouldDraw = shouldDraw,
            resizedTextStyle = resizedTextStyle,
            onSetInitialTextStyle = onSetInitialTextStyle,
            onAdjustTextStyle = onAdjustTextStyle
        )
    }
}

@Preview(showBackground = false, widthDp = 320, heightDp = 100, name = "CalculatorDisplay")
@Composable
fun PreviewCalculatorDisplay() {

    CalculatorDisplay(
        modifier = Modifier.fillMaxWidth(),
        text = "123.45",
        data = DisplayData(DisplayCalculatorInput.Standard),
        style = StyleCalculator.Standard,
        textStyle = TextStyle(
            fontSize = SIZE_FONT_DEFAULT.sp,
            fontWeight = FontWeight.Light
        ),
        buttonWidth = 8.dp,
        shouldDraw = true,
        resizedTextStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = VividGamboge
        ),
        onSetInitialTextStyle = { /* No-op */ },
        onAdjustTextStyle = { _, _, _, _, _ -> /* No-op */ }
    )
}