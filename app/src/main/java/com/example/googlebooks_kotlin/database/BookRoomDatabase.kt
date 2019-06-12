package com.example.googlebooks_kotlin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.googlebooks_kotlin.entities.Item
import com.example.googlebooks_kotlin.entities.typeconverter.VolumeInfoTypeConverter

@Database(entities = [Item::class], version = 1, exportSchema = false)
@TypeConverters(VolumeInfoTypeConverter::class)
abstract class BookRoomDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}