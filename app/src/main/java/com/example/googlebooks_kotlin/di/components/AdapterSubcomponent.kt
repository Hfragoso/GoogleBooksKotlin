//package com.example.googlebooks_kotlin.di.components
//
//import com.example.googlebooks_kotlin.di.modules.AdapterModule
//import com.example.googlebooks_kotlin.di.modules.ContextModule
//import com.example.googlebooks_kotlin.di.scopes.Activity
//import dagger.Subcomponent
//
//@Activity
//@Subcomponent(modules = [AdapterModule::class])
//interface AdapterSubcomponent {
//
//    @Subcomponent.Builder
//    interface Builder {
//        fun ContextModule(module: ContextModule): Builder
//        fun build(): AdapterSubcomponent
//    }
//}