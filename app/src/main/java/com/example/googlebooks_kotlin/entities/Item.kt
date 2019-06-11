package com.example.googlebooks_kotlin.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey
    @ColumnInfo(name = "item_id")
    @SerializedName("id")
    val id: String,
    @SerializedName("volumeInfo")
//    @TypeConverters(VolumeInfoTypeConverter::class)
    val volumeInfo: VolumeInfo?
) : Parcelable