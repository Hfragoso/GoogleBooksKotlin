package com.example.googlebooks_kotlin.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_author_table")
class BookAuthorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "book_id")
    var bookId: String,
    @ColumnInfo(name = "author_id")
    var authorId: Int
)