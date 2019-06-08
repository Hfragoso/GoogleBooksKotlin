package com.example.googlebooks_kotlin.di.components

import com.example.googlebooks_kotlin.bookslanding.view.BooksLandingActivity
import com.example.googlebooks_kotlin.di.modules.ContextModule
import com.example.googlebooks_kotlin.di.modules.NetworkModule
import com.example.googlebooks_kotlin.di.modules.UtilsModule
import com.example.googlebooks_kotlin.di.scopes.Application
import dagger.Component
import javax.inject.Singleton

@Application
@Component(modules = [ContextModule::class, UtilsModule::class, NetworkModule::class])
interface AppComponent {
    fun doInjection(booksLandingActivity: BooksLandingActivity)
}