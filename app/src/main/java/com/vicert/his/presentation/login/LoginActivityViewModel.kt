package com.vicert.his.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicert.his.data.api.LoginRequest
import com.vicert.his.data.api.LoginResponse
import com.vicert.his.data.remote.UserRepository
import com.vicert.his.utils.Resource
import kotlinx.coroutines.launch

class LoginActivityViewModel(
    val remoteRepository: UserRepository
) : ViewModel() {

    val loginResult: MutableLiveData<LoginState> = MutableLiveData()

    fun loginUser(email: String, pwd: String) {
        loginResult.value = LoginState.LoadingState
        viewModelScope.launch {
            try {
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
}

sealed class LoginState() {
    data class SuccessState(val result: LoginResponse?): LoginState()
    object LoadingState: LoginState()
    data class FailState(val msg: String?): LoginState()
}