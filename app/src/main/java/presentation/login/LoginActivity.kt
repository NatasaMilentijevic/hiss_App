package presentation.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vicert.his.databinding.ActivityLoginBinding
import data.api.LoginResponse
import utils.Resource

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.loginResult.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading()
                }

                is Resource.Success -> {
                    processLogin(it.data)
                }

                is Resource.Error -> {
                    processError(it.msg)
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

    private fun processLogin(data: LoginResponse?) {
        showToast("Success")
    }

    private fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}