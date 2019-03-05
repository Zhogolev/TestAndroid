package com.example.languagerecognizer.interfaces

import android.view.View

interface Recognaizer {
    fun getLanguage(text: String, parentView: View): String
}