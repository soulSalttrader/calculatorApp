package com.example.calculatorApp.presentation

import android.content.res.Configuration
import android.content.res.Resources

object PresentationUtils {

    fun isLandscape(): Boolean =
        Resources.getSystem().configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}