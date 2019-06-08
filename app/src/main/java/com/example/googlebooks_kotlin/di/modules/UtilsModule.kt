package com.example.googlebooks_kotlin.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.googlebooks_kotlin.bookslanding.datamodel.BooksRepository
import com.example.googlebooks_kotlin.utils.BooksService
import com.example.googlebooks_kotlin.utils.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class UtilsModule {
    @Singleton
    @Provides
    fun getRepository(booksService: BooksService): BooksRepository {
        return BooksRepository(booksService)
    }

    @Singleton
    @Provides
    internal fun getViewModelFactory(myRepository: BooksRepository): ViewModelProvider.Factory {
        return ViewModelFactory(myRepository)
    }
}


