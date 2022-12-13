package com.vicert.his.data.api.login

import com.google.gson.annotations.SerializedName

data class ResetPasswordResponse(
    @SerializedName("token")
    var token: String,
    @SerializedName("result")
    var result: Result
)