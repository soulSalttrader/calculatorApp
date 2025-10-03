package com.example.calculatorApp.model.state

interface HasError {
    val hasError: Boolean
    val errorMessage: String?
}