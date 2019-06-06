package com.example.googlebooks_kotlin.dataModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.googlebooks_kotlin.api.BooksService
import com.example.googlebooks_kotlin.model.BookList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BooksRepository @Inject constructor(booksService: BooksService) {
    val responseLiveData: MutableLiveData<BookList>? = null
    private val booksService = booksService

    fun fetchBooks(index: Int, maxResults: Int): LiveData<BookList>? {
        return fetchBooksFromApi(index, maxResults)
    }

    private fun fetchBooksFromApi(index: Int, maxResults: Int): LiveData<BookList>? {
        val call: Call<BookList>? = booksService.getBooks(index, maxResults)
        val data = MutableLiveData<BookList>()
        call?.enqueue(object : Callback<BookList> {
            override fun onFailure(call: Call<BookList>?, t: Throwable?) {
                responseLiveData?.apply { postValue(null) }
            }

            override fun onResponse(call: Call<BookList>?, response: Response<BookList>?) {
                data.value = response?.body()
            }
        })

        return data
    }
}