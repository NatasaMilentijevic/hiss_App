package com.vicert.his.data.api.login

import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(
    @SerializedName("email")
    var email: String,
    @SerializedName("oldPassword")
    var oldPassword: String,
    @SerializedName("newPassword")
    var newPassword: String,
    @SerializedName("confirmNewPassword")
    var confirmNewPassword: String

)
