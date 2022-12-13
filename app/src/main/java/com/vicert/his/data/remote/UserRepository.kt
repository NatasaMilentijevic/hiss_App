package com.vicert.his.data.remote

import com.vicert.his.data.api.login.LoginRequest
import com.vicert.his.data.api.login.LoginResponse
import com.vicert.his.data.api.login.ResetPasswordRequest
import com.vicert.his.data.api.login.ResetPasswordResponse
import com.vicert.his.utils.Resource

interface UserRepository {
    suspend fun getUserLogin(loginRequest: LoginRequest): Resource<LoginResponse>
    suspend fun getUserResetPassword(resetPasswordRequest: ResetPasswordRequest): Resource<ResetPasswordResponse>
}