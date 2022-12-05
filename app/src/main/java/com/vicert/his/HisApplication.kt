package com.vicert.his

import android.app.Application
import com.vicert.his.di.DaggerRetroComponent
import com.vicert.his.di.RetroComponent
import com.vicert.his.di.RetroModule

class HisApplication : Application() {

    private lateinit var retroComponent: RetroComponent

    override fun onCreate() {
        super.onCreate()

        retroComponent = DaggerRetroComponent.builder()
            .retroModule(RetroModule())
            .build()
    }

    fun getRetroComponent(): RetroComponent {
        return retroComponent
    }
}

