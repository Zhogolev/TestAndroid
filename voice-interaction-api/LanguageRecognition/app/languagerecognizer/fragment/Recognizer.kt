package com.example.languagerecognizer.fragment

import android.app.Fragment
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.ktor.client.HttpClient
import io.ktor.http.HttpHeaders.Host
import io.ktor.http.cio.Request
import kotlinx.android.synthetic.main.page_recognizer.view.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL


class Recognizer : Fragment() {
    companion object {
        private const val username: String = "6987a48d-342e-4a69-8adc-e65b1cc0b9da"
        private const val password: String = "MxYSIi6nQP2Y"
        private const val url: String =
            "https://gateway.watsonplatform.net/language-translator/api/v3/identify?version=2018-05-01"
        private val auth = android.util.Base64.encode("$username:$password".toByteArray(), 0)

    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater!!.inflate(com.example.languagerecognizer.R.layout.page_recognizer, container, false)

        view.button_recognizer.setOnClickListener {

            Log.d("asd", url)
            val connection = URL(url)
                .openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.addRequestProperty("Authorization", "Basic $auth")
            connection.addRequestProperty("Content-type", "text/plan")
            connection.readTimeout = 10000 // millis
            connection.connectTimeout = 15000
            //connection. = Host("gateway.watsonplatform.net")
            connection.doOutput = true
            Log.d("http ", "start connect")
            connection.connect()
            Log.d("http","after connect")
            val outputStream: OutputStream = connection.outputStream
            val outputWriter = OutputStreamWriter(outputStream)
            outputWriter.write(view.recognize_text.text.toString())
            outputWriter.flush()
            BufferedReader(InputStreamReader(connection.inputStream)).use {
                val response = StringBuffer()
                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                it.close()

                println(">>>> Response: $response")
                Log.d("recognizer", response.toString())
            }
            connection.disconnect()

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