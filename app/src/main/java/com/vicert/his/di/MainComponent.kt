package com.vicert.his.di

import com.vicert.his.presentation.login.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class, DataModule::class, LoginModule::class])
interface MainComponent {

    fun inject(activity: LoginActivity) {}
}