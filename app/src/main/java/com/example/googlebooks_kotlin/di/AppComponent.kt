package com.example.googlebooks_kotlin.di

import com.example.googlebooks_kotlin.bookslanding.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, UtilsModule::class])
interface AppComponent {
    fun doInjection(mainActivity: MainActivity)
}