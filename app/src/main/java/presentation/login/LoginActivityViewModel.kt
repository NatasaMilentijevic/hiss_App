package presentation.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.api.LoginRequest
import data.api.LoginResponse
import di.RetroServiceInterface
import utils.Resource
import kotlinx.coroutines.launch
import model.HisApplication
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class LoginActivityViewModel(
   application: Application
    ) : ViewModel() {

    @Inject
    lateinit var retroServiceInterface: RetroServiceInterface

    val loginResult: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    init {
        (application as HisApplication).getRetroComponent().inject(this)
    }

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

    fun makeApiCall(){
        val call: Call<> = retroServiceInterface.getDataFromAPI()
        call?.enqueue(object : Callback<>{

            //override fun: onFailure, onResponse

        })
    }
}