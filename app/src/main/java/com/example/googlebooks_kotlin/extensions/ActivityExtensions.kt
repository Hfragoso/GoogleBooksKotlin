package com.example.googlebooks_kotlin.extensions

import android.widget.ImageView
import com.example.googlebooks_kotlin.R
import com.squareup.picasso.Picasso

fun ImageView.loadImage(imageUrl: String?) {
    Picasso.get()
        .load(imageUrl)
        .placeholder(R.mipmap.ic_launcher)
        .into(this)
}