package com.vicert.his.presentation.login

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicert.his.data.api.login.LoginRequest
import com.vicert.his.data.api.login.LoginResponse
import com.vicert.his.data.api.login.ResetPasswordRequest
import com.vicert.his.data.api.login.ResetPasswordResponse
import com.vicert.his.data.remote.UserRepository
import com.vicert.his.utils.Constant
import com.vicert.his.utils.Constant.DIGIT_CASE_PATTERN
import com.vicert.his.utils.Constant.SPECIAL_CHAR_PATTERN
import com.vicert.his.utils.Constant.UPPER_CASE_PATTERN
import com.vicert.his.utils.Resource
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginActivityViewModel(
    val remoteRepository: UserRepository
) : ViewModel() {

    val loginResult: MutableLiveData<LoginState> = MutableLiveData()
    val resetResult: MutableLiveData<ResetState> = MutableLiveData()

    fun loginUser(email: String, pwd: String) {
        loginResult.value = LoginState.LoadingState
        viewModelScope.launch {
            try {
                if (!loginValidation(email, pwd)) {
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

    fun resetPassword(email: String, oldPassword: String, newPassword: String, confirmNewPassword: String) {
        resetResult.value = ResetState.LoadingState
        viewModelScope.launch {
            try {
                if (!resetValidation(email, oldPassword, newPassword, confirmNewPassword)) {
                   resetResult.postValue(ResetState.FailState("Invalid input. Password should be at least 8 characters long, " +
                           "contain upper case, number and speciaal character"))
                }else {
                    val resetRequest = ResetPasswordRequest(
                        email = email,
                        oldPassword = oldPassword,
                        newPassword = newPassword,
                        confirmNewPassword = confirmNewPassword
                    )

                    when (val response =
                        remoteRepository.getUserResetPassword(resetPasswordRequest = resetRequest)) {
                        is Resource.Success -> {
                            resetResult.postValue(ResetState.SuccessState(response.data))
                        }
                        is Resource.Error -> {
                            resetResult.postValue(ResetState.FailState(response.msg))
                        }
                    }
                }
            } catch (ex: Exception) {
                resetResult.postValue(ResetState.FailState(ex.message))
            }
        }
    }

    private val emailAddressPattern = Pattern.compile(Constant.EMAIL_ADDRESS_PATTERN)

    private fun loginValidation(email: String, password: String): Boolean {
        return if (email.isEmpty() || password.isEmpty() && !isValidString(email) && !isValidString(password)) {
            false
        } else {
            password.length >= Constant.MIN_PASSWORD_LENGTH
        }
    }

    private fun resetValidation(email: String, oldPassword: String, newPassword: String, confirmedPassword: String ): Boolean {
        return if (email.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty() || confirmedPassword.isEmpty()
            || !isValidString(email) || !newPassword.equals(confirmedPassword) || !UPPER_CASE_PATTERN.matcher(newPassword).find()
            || !SPECIAL_CHAR_PATTERN.matcher(newPassword).find() || !DIGIT_CASE_PATTERN.matcher(newPassword).find()) {
            false
        } else {
            newPassword.length >= Constant.MIN_PASSWORD_LENGTH
        }
    }

    private fun isValidString(str: String): Boolean {
        return emailAddressPattern.matcher(str).matches()
    }
}

sealed class LoginState {
    data class SuccessState(val result: LoginResponse?) : LoginState()
    object LoadingState : LoginState()
    data class FailState(val msg: String?) : LoginState()
}

sealed class ResetState {
    data class SuccessState(val result: ResetPasswordResponse?) : ResetState()
    object LoadingState : ResetState()
    data class FailState(val msg: String?) : ResetState()
}