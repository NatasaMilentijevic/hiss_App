package com.vicert.his.data.remote

import com.vicert.his.data.api.LoginRequest
import com.vicert.his.data.api.LoginResponse
import com.vicert.his.utils.Resource

interface UserRepository {
    suspend fun getUserLogin(loginRequest: LoginRequest): Resource<LoginResponse>
}