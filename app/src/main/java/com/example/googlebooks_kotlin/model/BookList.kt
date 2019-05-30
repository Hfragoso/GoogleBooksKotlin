package com.example.googlebooks_kotlin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class BookList(

    @SerializedName("kind") val kind: String?,
    @SerializedName("totalItems") val totalItems: Int?,
    @SerializedName("items") val items: List<Item>?
) : Serializable