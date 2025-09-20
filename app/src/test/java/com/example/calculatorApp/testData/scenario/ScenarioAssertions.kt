package com.example.calculatorApp.testData.scenario

inline fun <reified T : Scenario> Scenario.requireScenario(): T =
    this as? T ?: error("Requires ${T::class.simpleName} but got ${this::class.simpleName}")