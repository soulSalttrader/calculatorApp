package com.example.calculatorApp.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.calculatorApp.domain.action.CalculatorAction
import com.example.calculatorApp.domain.action.CalculatorActionHandler
import com.example.calculatorApp.model.state.CalculatorState
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

    val stateCal: StateFlow<CalculatorState> = savedStateHandle.getStateFlow(STATE_CAL, CalculatorState())
    val stateUi: StateFlow<CalculatorStateUI> = savedStateHandle.getStateFlow(STATE_UI, CalculatorStateUI.DEFAULT)
    val isLandscape: StateFlow<Boolean> = savedStateHandle.getStateFlow(ORIENTATION, false)

    private fun setState(newState: CalculatorState) {
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
}