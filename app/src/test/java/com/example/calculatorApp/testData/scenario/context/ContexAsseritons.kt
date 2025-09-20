package com.example.calculatorApp.testData.scenario.context

inline fun <reified T : Context> Context.requireContext(): T =
    this as? T ?: error("Requires ${T::class.simpleName} but got ${this::class.simpleName}")