package com.example.googlebooks_kotlin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageLinks(

    @SerializedName("thumbnail") val thumbnail: String?
) : Serializable