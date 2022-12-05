package com.vicert.his.di

import dagger.Component
import com.vicert.his.presentation.login.LoginActivityViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
interface RetroComponent {

    fun inject(loginViewModel: LoginActivityViewModel) {}
}