package com.example.googlebooks_kotlin.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VolumeInfo(
    @SerializedName("title")
    val title: String?,
    @SerializedName("authors")
    val authors: List<String>?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedDate")
    val publishedDate: String?,
    @SerializedName("imageLinks")
    val imageLinks: ImageLinks?
) : Parcelable