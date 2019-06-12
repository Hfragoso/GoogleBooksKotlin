package com.example.googlebooks_kotlin.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.googlebooks_kotlin.entities.Item

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Item)

    @get:Query("SELECT * FROM item_table")
    val allBooks: LiveData<List<Item>>

    @Query("DELETE FROM item_table")
    fun nukeTable()
}