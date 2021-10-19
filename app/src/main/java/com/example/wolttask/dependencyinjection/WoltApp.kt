package com.example.wolttask.dependencyinjection

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WoltApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext( this@WoltApp)
            modules(com.example.wolttask.modules)
        }
    }
}