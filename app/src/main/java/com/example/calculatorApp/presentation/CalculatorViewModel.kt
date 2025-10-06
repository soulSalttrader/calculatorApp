package com.example.calculatorApp.presentation

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.calculatorApp.components.ComponentsUtils.adjustTextStyle
import com.example.calculatorApp.domain.action.CalculatorAction
import com.example.calculatorApp.domain.action.CalculatorActionHandler
import com.example.calculatorApp.model.state.CalculatorStateDomain
import com.example.calculatorApp.model.state.CalculatorStateUI
import com.example.calculatorApp.presentation.PresentationUtils.isLandscape
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val actionHandler: CalculatorActionHandler,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    companion object {
        private const val STATE_CAL = "calculator_state"
        private const val STATE_UI = "ui_state"
        private const val ORIENTATION = "calculator_orientation"
    }

    val stateCal: StateFlow<CalculatorStateDomain> = savedStateHandle.getStateFlow(STATE_CAL, CalculatorStateDomain())
    val stateUi: StateFlow<CalculatorStateUI> = savedStateHandle.getStateFlow(STATE_UI, CalculatorStateUI.DEFAULT)
    val isLandscape: StateFlow<Boolean> = savedStateHandle.getStateFlow(ORIENTATION, false)

    private fun setState(newState: CalculatorStateDomain) {
        savedStateHandle[STATE_CAL] = newState
    }

    fun setOrientation() {
        val currentOrientation = isLandscape()
        savedStateHandle[ORIENTATION] = currentOrientation
    }

    private fun setStateUi(transform: (CalculatorStateUI) -> CalculatorStateUI) {
        savedStateHandle[STATE_UI] = transform(stateUi.value)
    }

    fun onAction(action: CalculatorAction) {
        val result = actionHandler.handleAction(action, stateCal.value)
        setState(result.newState)
    }

    fun setButtonWidth(width: Dp) {
        setStateUi { it.copy(buttonWidth = width.value) }
    }

    fun setInitialTextStyle(textStyle: TextStyle) {
        runCatching {
            setStateUi {
                it.copy(
                    textSize = textStyle.fontSize.value,
                    textWeight = textStyle.fontWeight?.weight ?: FontWeight.Light.weight,
                    textColor = textStyle.color.value.toLong()
                )
            }
        }.getOrElse { exception -> Log.e("CalculatorViewModel", "Failed to set initial text style", exception) }
    }

    fun adjustTextStyleIfNeeded(result: TextLayoutResult, currentStyle: TextStyle, fallbackFontSize: TextUnit, fontWeight: FontWeight, color: Color) {
        val adjustedStyle = adjustTextStyle(result, currentStyle, fallbackFontSize, fontWeight, color)
        setShouldDraw(!result.didOverflowWidth)
        setResizedTextStyle(adjustedStyle)
    }

    fun setShouldDraw(shouldDraw: Boolean) {
        setStateUi { it.copy(shouldDraw = shouldDraw) }
    }

    private fun setResizedTextStyle(textStyle: TextStyle) {
        setStateUi {
            it.copy(
                textSize = textStyle.fontSize.value,
                textWeight = textStyle.fontWeight?.weight ?: FontWeight.Light.weight,
                textColor = textStyle.color.value.toLong()
            )
        }
    }
}