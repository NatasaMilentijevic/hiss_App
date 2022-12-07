package com.vicert.his

import android.app.Application
import com.vicert.his.di.DaggerMainComponent
import com.vicert.his.di.MainComponent
import com.vicert.his.di.RetroModule

class HisApplication : Application() {

    private lateinit var mainComponent: MainComponent

    override fun onCreate() {
        super.onCreate()

        mainComponent = DaggerMainComponent.builder()
            .retroModule(RetroModule())
            .build()
    }

    fun getMainComponent(): MainComponent {
        return mainComponent
    }
}

