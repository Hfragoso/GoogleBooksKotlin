package com.example.googlebooks_kotlin.di.modules

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.googlebooks_kotlin.bookslanding.datamodel.BooksRepository
import com.example.googlebooks_kotlin.database.BookDao
import com.example.googlebooks_kotlin.database.BookRoomDatabase
import com.example.googlebooks_kotlin.utils.BooksService
import com.example.googlebooks_kotlin.utils.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class UtilsModule {
    @Singleton
    @Provides
    fun getRepository(
        booksService: BooksService,
        bookDao: BookDao
    ): BooksRepository {
        return BooksRepository(booksService, bookDao)
    }

    @Singleton
    @Provides
    internal fun getViewModelFactory(myRepository: BooksRepository): ViewModelProvider.Factory {
        return ViewModelFactory(myRepository)
    }

    @Singleton
    @Provides
    fun provideBookRoomDatabase(context: Context): BookRoomDatabase {
        return Room.databaseBuilder(context, BookRoomDatabase::class.java, "books_db").fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDaoBookAccess(bookRoomDatabase: BookRoomDatabase): BookDao {
        return bookRoomDatabase.bookDao()
    }
}


