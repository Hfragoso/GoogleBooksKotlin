package com.example.googlebooks_kotlin.database.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "author_table")
class AuthorEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "author_id")
    val id: Int,
    @ColumnInfo(name = "author_name")
    var name: String
) : Parcelable