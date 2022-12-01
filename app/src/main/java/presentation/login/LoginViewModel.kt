package presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.api.LoginRequest
import data.api.LoginResponse
import utils.Resource
import kotlinx.coroutines.launch
import repository.LoginRepository

class LoginViewModel(
    val userRepository: LoginRepository
    ) : ViewModel() {

    val loginResult: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    fun loginUser(email: String, pwd: String) {

        loginResult.value = Resource.Loading
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(
                    password = pwd,
                    email = email
                )
                val response = userRepository.loginUser(loginRequest = loginRequest)
                if (response?.code() == 200) {
                    loginResult.value = Resource.Success(response.body())
                } else {
                    loginResult.value = Resource.Error(response?.message())
                }

            } catch (ex: Exception) {
                loginResult.value = Resource.Error(ex.message)
            }
        }


    }
}