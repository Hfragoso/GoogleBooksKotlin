package com.example.googlebooks_kotlin.di.components

import com.example.googlebooks_kotlin.di.modules.ContextModule
import com.example.googlebooks_kotlin.di.modules.NetworkModule
import com.example.googlebooks_kotlin.di.modules.UtilsModule
import com.example.googlebooks_kotlin.screens.bookdetails.view.BookDetailsActivity
import com.example.googlebooks_kotlin.screens.bookslanding.view.BooksLandingActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UtilsModule::class, NetworkModule::class, ContextModule::class])
interface AppComponent {
    fun doInjection(booksLandingActivity: BooksLandingActivity)
    fun doInjection(bookDetailsActivity: BookDetailsActivity)
    fun adapterSubcomponentBuilder(): AdapterSubcomponent.Builder
}