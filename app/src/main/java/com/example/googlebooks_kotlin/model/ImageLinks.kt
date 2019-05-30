package com.example.googlebooks_kotlin.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageLinks(

    @SerializedName("thumbnail") val thumbnail: String?
) : Parcelable