package com.example.googlebooks_kotlin.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.googlebooks_kotlin.bookslanding.datamodel.BooksRepository
import com.example.googlebooks_kotlin.di.scopes.Application
import com.example.googlebooks_kotlin.utils.BooksService
import com.example.googlebooks_kotlin.utils.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class UtilsModule {
    @Application
    @Provides
    fun getRepository(booksService: BooksService): BooksRepository {
        return BooksRepository(booksService)
    }

    @Application
    @Provides
    internal fun getViewModelFactory(myRepository: BooksRepository): ViewModelProvider.Factory {
        return ViewModelFactory(myRepository)
    }
}


