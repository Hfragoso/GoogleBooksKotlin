package com.example.googlebooks_kotlin.utils

import com.example.googlebooks_kotlin.models.BookList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksService {
    @GET("volumes")
    fun getBooks(@Query("q") query: String, @Query("startIndex") index: Int, @Query("maxResults") maxResults: Int): Single<BookList>
}