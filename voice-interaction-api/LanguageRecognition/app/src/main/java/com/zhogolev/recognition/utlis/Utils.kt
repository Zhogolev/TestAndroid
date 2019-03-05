package com.zhogolev.recognition.utlis

class Utils {
    companion object {
        fun getBasicToken(account: String, password: String): String =
           "Basic " + android.util.Base64.decode("$account:$password", android.util.Base64.DEFAULT).toString()
    }
}