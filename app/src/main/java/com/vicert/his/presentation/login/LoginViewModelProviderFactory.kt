package com.vicert.his.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vicert.his.repository.LoginRepository
import javax.inject.Singleton

class LoginViewModelProviderFactory(
    val loginRepository: LoginRepository
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginActivityViewModel() as T
    }
}