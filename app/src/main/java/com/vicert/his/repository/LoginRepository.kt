package com.vicert.his.repository

import com.vicert.his.data.api.UserApi
import com.vicert.his.data.api.LoginRequest
import com.vicert.his.data.api.LoginResponse
import retrofit2.Response

class LoginRepository(
    val loginActivity: LoginResponse
) {

    suspend fun loginUser(loginRequest: LoginRequest) : Response<LoginResponse>?{
        return UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
}