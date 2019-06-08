package com.example.googlebooks_kotlin.utils

import android.app.Activity
import android.app.Application
import com.example.googlebooks_kotlin.di.components.AppComponent
import com.example.googlebooks_kotlin.di.components.DaggerAppComponent
import com.example.googlebooks_kotlin.di.modules.ContextModule

class App : Application() {

    lateinit var component: AppComponent private set

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

    companion object {
        fun getApplication(activity: Activity) = activity.application as App
    }
}