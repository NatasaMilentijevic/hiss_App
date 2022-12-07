package com.vicert.his.data.repository

import com.vicert.his.data.api.LoginRequest
import com.vicert.his.data.api.LoginResponse
import com.vicert.his.data.remote.UserRepository
import com.vicert.his.di.RetroServiceInterface
import com.vicert.his.utils.Resource

class UserRepositoryImpl(
    private val retroServiceInterface: RetroServiceInterface
) : UserRepository {

    override suspend fun getUserLogin(loginRequest: LoginRequest): Resource<LoginResponse> {
        return try {
            val userResponse = retroServiceInterface.loginUser(loginRequest)
            Resource.Success(userResponse)
        } catch (e: java.lang.Exception) {
            Resource.Error(e.message)
        }
    }

}