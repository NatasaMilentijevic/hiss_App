package com.vicert.his.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vicert.his.R
import com.vicert.his.presentation.login.LoginPref

class HomeActivity : AppCompatActivity() {

    lateinit var session: LoginPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        session = LoginPref(this)

        session.checkLogin()

        var user: HashMap<String, String> = session.getUserDetais()






    }
}