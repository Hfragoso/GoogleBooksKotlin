package com.example.googlebooks_kotlin.di.components

import com.example.googlebooks_kotlin.di.modules.AdapterModule
import com.example.googlebooks_kotlin.di.modules.RoomModule
import com.example.googlebooks_kotlin.di.scopes.Activity
import com.example.googlebooks_kotlin.screens.bookslanding.adapter.BooksLandingAdapter
import dagger.Subcomponent

@Activity
@Subcomponent(modules = [AdapterModule::class, RoomModule::class])
interface AdapterSubcomponent {

    fun buildAdapter(): BooksLandingAdapter

    @Subcomponent.Builder
    interface Builder {
        fun build(): AdapterSubcomponent
    }
}