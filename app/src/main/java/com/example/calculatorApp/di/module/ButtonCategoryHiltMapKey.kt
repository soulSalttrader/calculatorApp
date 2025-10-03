package com.example.calculatorApp.di.module

import com.example.calculatorApp.di.ButtonCategoryHiltKey
import dagger.MapKey

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ButtonCategoryHiltMapKey(val value: ButtonCategoryHiltKey)