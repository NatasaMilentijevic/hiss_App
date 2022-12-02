package di

import data.api.LoginResponse
import retrofit2.Call
import retrofit2.http.POST

interface RetroServiceInterface {

    @POST("/identity/login")
    fun getDataFromAPI() : Call<LoginResponse>?

    //@POST("") for reset password

}