package com.vicert.his.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicert.his.data.api.LoginRequest
import com.vicert.his.data.api.LoginResponse
import com.vicert.his.di.RetroServiceInterface
import com.vicert.his.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginActivityViewModel() : ViewModel() {

    @Inject
    lateinit var retroServiceInterface: RetroServiceInterface

    val loginResult: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    fun loginUser(email: String, pwd: String) {

        loginResult.value = Resource.Loading
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(
                    password = pwd,
                    email = email
                )
                val response = retroServiceInterface.loginUser(loginRequest = loginRequest)
                if (response.code() == 200) {
                    loginResult.value = Resource.Success(response.body())
                } else {
                    loginResult.value = Resource.Error(response.message())
                }

            } catch (ex: Exception) {
                loginResult.value = Resource.Error(ex.message)
            }
        }
    }
}