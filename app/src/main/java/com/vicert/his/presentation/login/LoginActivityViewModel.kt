package com.vicert.his.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicert.his.data.api.LoginRequest
import com.vicert.his.data.api.LoginResponse
import com.vicert.his.data.remote.UserRepository
import com.vicert.his.utils.Resource
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginActivityViewModel(
    val remoteRepository: UserRepository
) : ViewModel() {

    val loginResult: MutableLiveData<LoginState> = MutableLiveData()

    fun loginUser(email: String, pwd: String) {
        loginResult.value = LoginState.LoadingState
        viewModelScope.launch {
            try {
                if(!loginValidation(email, pwd)) {
                    loginResult.postValue(LoginState.FailState("Invalid input. Please, try again."))
                }
                val loginRequest = LoginRequest(
                    password = pwd,
                    email = email
                )

                when (val response = remoteRepository.getUserLogin(loginRequest = loginRequest)) {
                    is Resource.Success -> {
                        loginResult.postValue(LoginState.SuccessState(response.data))
                    }
                    is Resource.Error -> {
                        loginResult.postValue(LoginState.FailState(response.msg))
                    }
                }

            } catch (ex: Exception) {
                loginResult.postValue(LoginState.FailState(ex.message))
            }
        }
    }
    val emailAdressPattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    fun loginValidation(email: String, password: String): Boolean {
        return if (email.isEmpty() || password.isEmpty() && !isValidString(email) && !isValidString(password))  {
            false
        } else {
            password.length >= 6
        }
    }
    fun isValidString(str: String): Boolean{
        return emailAdressPattern.matcher(str).matches()
    }

}

sealed class LoginState() {
    data class SuccessState(val result: LoginResponse?): LoginState()
    object LoadingState: LoginState()
    data class FailState(val msg: String?): LoginState()
}