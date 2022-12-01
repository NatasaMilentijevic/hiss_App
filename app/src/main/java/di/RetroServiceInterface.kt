package di

import data.api.LoginResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInterface {

    //add when load swagger
    @GET("")
    fun getDataFromAPI(@Query(" ")query: String) : Call<LoginResponse>?

}