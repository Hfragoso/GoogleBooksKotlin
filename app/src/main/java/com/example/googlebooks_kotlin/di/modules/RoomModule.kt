package com.example.googlebooks_kotlin.di.modules

import android.content.Context
import androidx.room.Room
import com.example.googlebooks_kotlin.database.BookDao
import com.example.googlebooks_kotlin.database.BookRoomDatabase
import com.example.googlebooks_kotlin.di.scopes.Activity
import dagger.Module
import dagger.Provides

@Module
class RoomModule {
    @Activity
    @Provides
    fun provideBookRoomDatabase(context: Context): BookRoomDatabase {
        return Room.databaseBuilder(context, BookRoomDatabase::class.java, "books_db").fallbackToDestructiveMigration()
            .build()
    }

    @Activity
    @Provides
    fun provideDaoBookAccess(bookRoomDatabase: BookRoomDatabase): BookDao {
        return bookRoomDatabase.bookDao()
    }
}