package com.example.googlebooks_kotlin.utils

import com.example.googlebooks_kotlin.entities.BookList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksService {

    @GET("volumes?q=android")
    fun getBooks(@Query("startIndex") index: Int, @Query("maxResults") maxResults: Int): Call<BookList>
}