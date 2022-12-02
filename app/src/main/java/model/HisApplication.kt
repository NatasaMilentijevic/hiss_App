package model

import android.app.Application
import dagger.internal.MapProviderFactory.builder
import di.RetroComponent
import di.RetroModule
import java.util.stream.DoubleStream.builder

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

