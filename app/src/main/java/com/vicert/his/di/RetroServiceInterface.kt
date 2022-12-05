package com.vicert.his.di

import com.vicert.his.data.api.LoginRequest
import com.vicert.his.data.api.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RetroServiceInterface {

    @POST("identity/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest) : Response<LoginResponse>

}