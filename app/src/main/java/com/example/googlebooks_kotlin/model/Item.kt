package com.example.googlebooks_kotlin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item(

    @SerializedName("id") val id: String?,
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo?
) : Serializable