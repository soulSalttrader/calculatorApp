package com.example.calculatorApp.model.state

import com.example.calculatorApp.model.elements.button.Button

interface HasInteraction {
    val activeButton: Button?
}