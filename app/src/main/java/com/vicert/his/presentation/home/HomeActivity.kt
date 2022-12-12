package com.vicert.his.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vicert.his.databinding.ActivityHomeBinding
import com.vicert.his.presentation.login.LoginPref

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var session: LoginPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // var user: HashMap<String, String> = session.getUserDetails()

        binding.btnLogout.setOnClickListener {
            session.getUserDetails()
            session = LoginPref(this)
            session.checkLogin()
            session.logoutUser()
        }

    }
}