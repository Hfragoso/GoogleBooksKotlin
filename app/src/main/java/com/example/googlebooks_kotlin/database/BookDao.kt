package com.example.googlebooks_kotlin.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.googlebooks_kotlin.entities.Item

@Dao
interface BookDao {

//    @Query("SELECT * FROM book_item_table WHERE page = :page")
//    fun getBooksByPage(page: Int): LiveData<List<Item>>

    @Query("SELECT * FROM item_table")
    fun getAllBooks(): LiveData<List<Item>>

    @Insert
    fun insert(item: Item)
}