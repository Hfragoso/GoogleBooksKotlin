package com.example.googlebooks_kotlin.database.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "book_table")
data class BookEntity(
    @PrimaryKey
    @ColumnInfo(name = "book_id")
    val id: String,
    @ColumnInfo(name = "book_title")
    var title: String?,
    @ColumnInfo(name = "book_description")
    var description: String?,
    @ColumnInfo(name = "book_published_date")
    var publishedDate: String?,
    @ColumnInfo(name = "book_thumbnail")
    var thumbnail: String?
) : Parcelable