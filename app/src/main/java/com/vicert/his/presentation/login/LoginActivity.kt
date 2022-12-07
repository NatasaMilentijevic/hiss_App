package com.vicert.his.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vicert.his.databinding.ActivityLoginBinding
import com.vicert.his.presentation.home.HomeActivity
import com.vicert.his.utils.Resource
import com.vicert.his.utils.toast

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginActivityViewModel: LoginActivityViewModel
    lateinit var session: LoginPref

    private val viewModel by viewModels<LoginActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        session = LoginPref(this)

        if (session.isLoggedIn()) {
            var i: Intent = Intent(applicationContext, HomeActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            finish()
        }

        initViewModel()

        viewModel.loginResult.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading()
                }

                is Resource.Success -> {
                    toast(it.data?.token)
                }

                is Resource.Error -> {
                    toast(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextEmailAddress.text.toString().trim()
            val pwd = binding.editTextPassword.text.toString().trim()
            viewModel.loginUser(email = email, pwd = pwd)

            if (email.isEmpty()) {
                session.createLoginSession(email, pwd)
                var i: Intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(i)
                finish()
            } else {
                toast("Login failed. Please try again. ")
            }
        }
    }


    private fun showLoading() {
        binding.progressBarLogin.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressBarLogin.visibility = View.GONE
    }

    private fun initViewModel() {
        loginActivityViewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
    }
}