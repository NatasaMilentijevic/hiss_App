package data.api

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("errors")
    var errors: List<MutableList<String>>,
    @SerializedName("token")
    var token: String,
    @SerializedName("result")
    var result: Result,
    @SerializedName("roles")
    var roles: String
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
