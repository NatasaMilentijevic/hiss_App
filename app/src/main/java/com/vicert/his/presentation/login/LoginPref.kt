package com.vicert.his.presentation.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.vicert.his.utils.Constant

class LoginPref(private var con: Context) {

    private var pref: SharedPreferences
    private var editor: SharedPreferences.Editor
    private var privateMode: Int = 0

    init {
        pref = con.getSharedPreferences(Constant.PREF_NAME, privateMode)
        editor = pref.edit()
    }


    fun checkLogin() {
        if (!this.isLoggedIn()) {
            val i = Intent(con, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            con.startActivity(i)
        }
    }

    fun getUserDetails(): HashMap<String, String> {
        val user: Map<String, String> = HashMap()
        (user as HashMap).put(Constant.KEY_TOKEN, pref.getString(Constant.KEY_TOKEN, null)!!)
        return user
    }

    fun logoutUser() {
        editor.clear()
        editor.commit()
        val i = Intent(con, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)


        con.startActivity(i)
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(Constant.IS_LOGIN, false)
    }
}