package com.example.googlebooks_kotlin.di.components

import com.example.googlebooks_kotlin.bookslanding.view.BooksLandingActivity
import com.example.googlebooks_kotlin.di.modules.AdapterModule
import com.example.googlebooks_kotlin.di.modules.ContextModule
import com.example.googlebooks_kotlin.di.modules.NetworkModule
import com.example.googlebooks_kotlin.di.modules.UtilsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UtilsModule::class, NetworkModule::class, ContextModule::class, AdapterModule::class])
interface AppComponent {
    fun doInjection(booksLandingActivity: BooksLandingActivity)
//    fun adapterSubcomponentBuilder(): AdapterSubcomponent.Builder
}