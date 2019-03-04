package com.example.languagerecognizer.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.languagerecognizer.R
import com.example.languagerecognizer.services.RecognizerImpl
import kotlinx.android.synthetic.main.page_recognizer.view.*

class Recognizer : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater!!.inflate(R.layout.page_recognizer, container, false)

        view.button_recognizer.setOnClickListener{
          Snackbar.make(it, "Start recognizing text",200).show()
          RecognizerImpl.getLanguage(view.recognize_text.text.toString(),it)
        }
        return view

    }

}