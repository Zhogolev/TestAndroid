package com.zhogolev.recognition.services

import android.support.design.widget.Snackbar
import android.view.View
import com.zhogolev.recognition.interfaces.Recognaizer

object RecognizerImpl : Recognaizer {
    override fun getLanguage(text: String, parentView: View): String {
        return when (text) {
            "" -> {
                val response = "Sorry, text is empty"
                Snackbar.make(parentView, response, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

                response
            }
            else -> {
                Snackbar.make(parentView, text, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                text
            }
        }
    }
}