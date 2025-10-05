package com.example.calculatorApp.components


import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.calculatorApp.R
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.model.layout.button.ButtonLayoutRegular
import com.example.calculatorApp.model.state.CalculatorStateDomain
import com.example.calculatorApp.model.styles.Style
import com.example.calculatorApp.model.styles.StyleCalculator
import com.example.calculatorApp.presentation.CalculatorViewModel

@Composable
fun CalculatorButtonStateful(
    modifier: Modifier = Modifier,
    data: ButtonData,
    style: Style = StyleCalculator.Standard,
    viewModel: CalculatorViewModel,
    onClick: () -> Unit
) {
    val state by viewModel.stateCal.collectAsStateWithLifecycle()
    CalculatorButton(
        modifier = modifier.clickable { onClick() },
        data = data,
        style = style,
        state = state,
    )
}

@Composable
fun CalculatorButton(
    modifier: Modifier = Modifier,
    data: ButtonData,
    style: Style = StyleCalculator.Standard,
    state: CalculatorStateDomain = CalculatorStateDomain(),
) {

    val shouldHighlight = data.element.shouldHighlight(state)
    val elementStyle = data.element.getStyle(style.buttonStyle)

    val buttonColor = if (shouldHighlight) colorResource(R.color.button_binary_background_highlight) else elementStyle.backgroundColor
    val textColor = if (shouldHighlight) colorResource(R.color.button_binary_foreground_highlight) else elementStyle.foregroundColor

    CalculatorStyledBox(
        modifier = modifier,
        role = Role.Button,
        layout = data.layout,
        backgroundColor = buttonColor,
        shape = data.layout.shape,
        contentDescription = data.element.symbol.label
    ) {

        Text(
            text = data.element.symbol.label,
            fontSize = data.layout.sizeFont,
            fontWeight = data.layout.weightFont,
            textAlign = data.layout.alignText,
            color = textColor,
            modifier = data.layout.modifier
        )
    }
}

@Preview
@Composable
fun PreviewCalculatorButtonHighlight() {
    val buttonData = ButtonData(
        element = ButtonCalculatorBinary.Multiplication,
        layout = ButtonLayoutRegular()
    )
    val state = CalculatorStateDomain(
        activeButton = ButtonCalculatorBinary.Multiplication
    )

    CalculatorButton(
        data = buttonData,
        style = StyleCalculator.Standard,
        state = state,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PreviewCalculatorButtonDefault() {
    val buttonData = ButtonData(
        element = ButtonCalculatorBinary.Multiplication,
        layout = ButtonLayoutRegular()
    )
    val state = CalculatorStateDomain(
        activeButton = ButtonCalculatorNumber.Three
    )

    CalculatorButton(
        data = buttonData,
        style = StyleCalculator.Standard,
        state = state,
    )
}