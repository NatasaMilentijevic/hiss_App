package com.vicert.his.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vicert.his.HisApplication
import com.vicert.his.databinding.ActivityLoginBinding
import com.vicert.his.presentation.base.ViewModelFactory
import com.vicert.his.presentation.home.HomeActivity
import com.vicert.his.utils.toast
import java.util.regex.Pattern
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var session: LoginPref

    @Inject
    lateinit var loginVMFactory: ViewModelFactory<LoginActivityViewModel>

    private val viewModel: LoginActivityViewModel by viewModels { loginVMFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as HisApplication).getMainComponent().inject(this)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        session = LoginPref(this)

        if (session.isLoggedIn()) {
            val i = Intent(applicationContext, HomeActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            finish()
        }

        viewModel.loginResult.observe(this) {
            when (it) {
                is LoginState.LoadingState -> {
                    showLoading()
                }
                is LoginState.SuccessState -> {
                    toast(it.result?.token)
                    stopLoading()
                }

                is LoginState.FailState -> {
                    toast(it.msg)
                    stopLoading()
                }
            }
        }

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextEmailAddress.text.toString().trim()
            val pwd = binding.editTextPassword.text.toString().trim()

            viewModel.loginUser(email = email, pwd = pwd)

            if (session.isLoggedIn()) {
                val i = Intent(applicationContext, HomeActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(i)
                finish()
            }

        }
    }

    private fun showLoading() {
        binding.progressBarLogin.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressBarLogin.visibility = View.GONE
    }
}