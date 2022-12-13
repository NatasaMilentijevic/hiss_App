package com.vicert.his.utils

object Constant {

    const val BASE_URL = "http://192.168.100.38:81/api/"
    const val EMPTY = ""
    const val EMAIL_ADDRESS_PATTERN = (
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
            )
    const val PREF_NAME = "Login_Preference"
    const val IS_LOGIN = "isLogin"
    const val KEY_TOKEN = "token"

}