package repository

import data.api.UserApi
import data.api.LoginRequest
import data.api.LoginResponse
import presentation.login.LoginActivity
import retrofit2.Response

class LoginRepository(
    val loginActivity: LoginResponse) {

    suspend fun loginUser(loginRequest: LoginRequest) : Response<LoginResponse>?{
        return UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
}