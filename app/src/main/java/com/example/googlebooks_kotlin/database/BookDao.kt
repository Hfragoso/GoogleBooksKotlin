package com.example.googlebooks_kotlin.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.googlebooks_kotlin.entities.Item

@Dao
interface BookDao {

    @Query("SELECT * from book_item_table")
    fun getAllWords(): LiveData<List<Item>>

    @Insert
    fun insert(word: Item)
}