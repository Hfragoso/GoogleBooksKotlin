package com.example.googlebooks_kotlin.models

import com.google.gson.annotations.SerializedName

data class BookList(

    @SerializedName("kind") val kind: String?,
    @SerializedName("totalItems") val totalItems: Int?,
    @SerializedName("items") val items: List<Item>?
)