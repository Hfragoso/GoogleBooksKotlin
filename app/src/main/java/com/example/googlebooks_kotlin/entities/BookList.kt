package com.example.googlebooks_kotlin.entities

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookList(

    @SerializedName("kind") val kind: String?,
    @SerializedName("totalItems") val totalItems: Int?,
    @SerializedName("items") val items: List<Item>?
) : Parcelable