package com.example.googlebooks_kotlin.di.components

import com.example.googlebooks_kotlin.bookslanding.adapter.BooksAdapter
import com.example.googlebooks_kotlin.di.modules.AdapterModule
import com.example.googlebooks_kotlin.di.scopes.Activity
import dagger.Subcomponent

@Activity
@Subcomponent(modules = [AdapterModule::class])
interface AdapterSubcomponent {

    fun buildAdapter(): BooksAdapter

    @Subcomponent.Builder
    interface Builder {
        fun build(): AdapterSubcomponent
    }
}