package com.vicert.his.data.repository

import com.vicert.his.data.api.login.LoginRequest
import com.vicert.his.data.api.login.LoginResponse
import com.vicert.his.data.api.login.ResetPasswordRequest
import com.vicert.his.data.api.login.ResetPasswordResponse
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

    override suspend fun getUserResetPassword(resetPasswordRequest: ResetPasswordRequest): Resource<ResetPasswordResponse> {
        return try {
            val resetResponse = retroServiceInterface.resetPassword(resetPasswordRequest)
            Resource.Success(resetResponse)
        } catch (e: java.lang.Exception) {
            Resource.Error(e.message)
        }
    }
}