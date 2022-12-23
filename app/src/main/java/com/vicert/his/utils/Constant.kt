package com.vicert.his.utils

import java.util.regex.Pattern

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
    const val MIN_PASSWORD_LENGTH = 8
    val UPPER_CASE_PATTERN = Pattern.compile("[A-Z ]")
    val SPECIAL_CHAR_PATTERN = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)
    val DIGIT_CASE_PATTERN = Pattern.compile("[0-9 ]")

}