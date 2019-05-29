package com.example.googlebooks_kotlin.model

import com.google.gson.annotations.SerializedName


data class BookList (

    @SerializedName("kind") val kind : String,
    @SerializedName("totalItems") val totalItems : Int,
    @SerializedName("items") var items : MutableList<Item>
)