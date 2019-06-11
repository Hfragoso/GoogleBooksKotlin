package com.example.googlebooks_kotlin.entities

import android.os.Parcelable
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VolumeInfo(
    @SerializedName("title") val title: String?,
    @Embedded
    @SerializedName("authors") val authors: List<String>?,
    @SerializedName("description") val description: String?,
    @SerializedName("publishedDate") val publishedDate: String?,
    @Embedded
    @SerializedName("imageLinks") val imageLinks: ImageLinks?
) : Parcelable {
    constructor() : this("", emptyList<String>(), "", "", ImageLinks(""))
}