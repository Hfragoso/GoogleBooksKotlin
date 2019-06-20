package com.example.googlebooks_kotlin.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "author_table")
class AuthorEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "author_id")
    val id: Int,
    @ColumnInfo(name = "author_name")
    var name: String
)