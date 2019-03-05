package com.zhogolev.recognition.fragment

import android.app.Fragment
import android.content.Entity
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhogolev.recognition.R
import kotlinx.android.synthetic.main.page_recognizer.view.*
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.ContentType
import org.apache.http.entity.HttpEntityWrapperHC4
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
import java.io.BufferedReader


class Recognizer : Fragment() {
    companion object {
        private const val username: String = "6987a48d-342e-4a69-8adc-e65b1cc0b9da"
        private const val password: String = "MxYSIi6nQP2Y"
        private const val url: String =
            "https://gateway.watsonplatform.net/language-translator/api/v3/identify?version=2018-05-01"
        private val auth = android.util.Base64.encode("$username:$password".toByteArray(), 0)

    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater!!.inflate(R.layout.page_recognizer, container, false)

        view.button_recognizer.setOnClickListener {

            val httpClient = HttpClientBuilder.create().build()
            val request:HttpPost = HttpPost(url)
            request.setHeader("Content-type","text/plan")
            request.setHeader("Authorization", "Basic $auth")
            val body = view.recognize_text.text.toString()


            val response  = httpClient.execute(request)

            val responseBody = response.entity.content.bufferedReader().use(BufferedReader::readText)


        }
        return view
    }

    class LanguageTask(private val view: View) : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg p0: String?): String {
            return p0[0].toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Snackbar.make(view, result.toString(), 200).show()
        }
    }


}