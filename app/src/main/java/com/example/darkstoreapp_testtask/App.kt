package com.example.darkstoreapp_testtask

import android.app.Application
import com.example.darkstoreapp_testtask.data.di.AppComponent
import com.example.darkstoreapp_testtask.data.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}