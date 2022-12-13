package com.vicert.his.di

import com.vicert.his.data.api.login.LoginRequest
import com.vicert.his.data.api.login.LoginResponse
import com.vicert.his.data.api.login.ResetPasswordRequest
import com.vicert.his.data.api.login.ResetPasswordResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RetroServiceInterface {

    @POST("identity/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse

    @POST("identity/login")
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): ResetPasswordResponse

}