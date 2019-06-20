package com.example.googlebooks_kotlin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.googlebooks_kotlin.database.entities.AuthorEntity
import com.example.googlebooks_kotlin.database.entities.BookAuthorEntity
import com.example.googlebooks_kotlin.database.entities.BookEntity
import com.example.googlebooks_kotlin.models.typeconverter.VolumeInfoTypeConverter

@Database(
    entities = [BookEntity::class, AuthorEntity::class, BookAuthorEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(VolumeInfoTypeConverter::class)
abstract class BookRoomDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}