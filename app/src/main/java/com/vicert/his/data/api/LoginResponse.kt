package com.vicert.his.data.api

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    var token: String,
    @SerializedName("result")
    var result: Result
)

data class Result(
    val seniority: String,
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val address: String,
    val phone: String,
    val dateOfBirth: String
)
