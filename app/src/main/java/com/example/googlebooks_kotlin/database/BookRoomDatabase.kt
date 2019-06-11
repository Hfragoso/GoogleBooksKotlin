package com.example.googlebooks_kotlin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.googlebooks_kotlin.entities.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class BookRoomDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}