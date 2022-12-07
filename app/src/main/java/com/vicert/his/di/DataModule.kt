package com.vicert.his.di

import com.vicert.his.data.remote.UserRepository
import com.vicert.his.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun providerRemoteRepo(
        retroServiceInterface: RetroServiceInterface
    ): UserRepository = UserRepositoryImpl (retroServiceInterface)

}