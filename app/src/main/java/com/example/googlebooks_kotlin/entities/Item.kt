package com.example.googlebooks_kotlin.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "book_item_table")
data class Item(

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id") val id: String,
//    @ColumnInfo(name = "volumeInfo")
    @Embedded
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo?,
    @ColumnInfo(name = "page")
    val page: Int
) : Parcelable {
    constructor() : this("", VolumeInfo("", emptyList<String>(),"","",ImageLinks("")),0)
}