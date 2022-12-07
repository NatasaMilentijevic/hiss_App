package com.vicert.his.di

import com.vicert.his.data.remote.UserRepository
import com.vicert.his.presentation.base.ViewModelFactory
import com.vicert.his.presentation.login.LoginActivityViewModel
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    fun provideLogin(
        remoteRepo: UserRepository
    ): ViewModelFactory<LoginActivityViewModel> {
        return ViewModelFactory { LoginActivityViewModel(remoteRepo) }
    }
}