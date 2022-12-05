package com.vicert.his.presentation.login

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vicert.his.databinding.ActivityLoginBinding
import com.vicert.his.data.api.LoginResponse
import com.vicert.his.utils.Resource
import com.vicert.his.utils.toast

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginActivityViewModel: LoginActivityViewModel

    private val viewModel by viewModels<LoginActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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
            doLogin()
        }
    }

    private fun doLogin() {
        val email = binding.editTextEmailAddress.text.toString()
        val pwd = binding.editTextPassword.text.toString()
        viewModel.loginUser(email = email, pwd = pwd)
    }


    private fun showLoading() {
        binding.progressBarLogin.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressBarLogin.visibility = View.GONE
    }

    private fun initViewModel(){
        loginActivityViewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
    }
}