package com.vicert.his.di
import dagger.Module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.vicert.his.utils.Constant.BASE_URL
import dagger.Provides
import javax.inject.Singleton

@Module
class RetroModule {

    @Singleton
    @Provides
    fun getRetroServiceInstanceInterface(retrofit: Retrofit): RetroServiceInterface {
        return retrofit.create(RetroServiceInterface::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}