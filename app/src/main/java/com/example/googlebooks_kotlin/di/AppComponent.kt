package com.example.googlebooks_kotlin.di

import com.example.googlebooks_kotlin.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, UtilsModule::class])
interface AppComponent {
    fun doInjection(mainActivity: MainActivity)
}