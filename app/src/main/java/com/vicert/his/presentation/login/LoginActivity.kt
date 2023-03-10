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
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var session: LoginPref

    @Inject
    lateinit var loginVMFactory: ViewModelFactory<LoginActivityViewModel>

    private val viewModel: LoginActivityViewModel by viewModels { loginVMFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as HisApplication).getMainComponent().inject(this)

        val homeIntent =
            Intent(this, HomeActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        session = LoginPref(this)

        if (session.isLoggedIn()) {
            startActivity(homeIntent)
            finish()
        }

        viewModel.loginResult.observe(this) {
            when (it) {
                is LoginState.LoadingState -> {
                    showLoading()
                }
                is LoginState.SuccessState -> {
                    stopLoading()
                    startActivity(homeIntent)
                    finish()
                }

                is LoginState.FailState -> {
                    toast(it.msg)
                    stopLoading()
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmailLogin.text.toString().trim()
            val pwd = binding.etPasswordLogin.text.toString().trim()
            viewModel.loginUser(email = email, pwd = pwd)
        }

        binding.btnForgotPassword.setOnClickListener {
            ResetPasswordSheetDialogFragment().show(supportFragmentManager, "forgotPasswordTag")
        }
    }

    private fun showLoading() {
        binding.pbLogin.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.pbLogin.visibility = View.GONE
    }

}