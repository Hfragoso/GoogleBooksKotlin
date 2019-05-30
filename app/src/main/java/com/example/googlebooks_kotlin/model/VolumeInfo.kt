package com.example.googlebooks_kotlin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VolumeInfo(

    @SerializedName("title") val title: String?,
    @SerializedName("authors") val authors: List<String>?,
    @SerializedName("description") val description: String?,
    @SerializedName("publishedDate") val publishedDate: String?,
    @SerializedName("imageLinks") val imageLinks: ImageLinks?
) : Serializable