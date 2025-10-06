package com.example.calculatorApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.calculatorApp.components.Calculator
import com.example.calculatorApp.presentation.CalculatorViewModel
import com.example.calculatorApp.ui.theme.CalculatorAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorAppTheme {
                val viewModel: CalculatorViewModel = hiltViewModel()
                viewModel.setOrientation()

                Calculator(viewModel = viewModel)
            }
        }
    }
}