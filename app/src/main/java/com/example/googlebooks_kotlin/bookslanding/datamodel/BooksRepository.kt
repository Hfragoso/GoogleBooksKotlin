package com.example.googlebooks_kotlin.bookslanding.datamodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.googlebooks_kotlin.utils.BooksService
import com.example.googlebooks_kotlin.entities.BookList
import com.example.googlebooks_kotlin.utils.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BooksRepository @Inject constructor(private val booksService: BooksService) {
    val responseLiveData: MutableLiveData<Status> = MutableLiveData()

    fun fetchBooks(index: Int, maxResults: Int): LiveData<Status> {
        return fetchBooksFromApi(index, maxResults)
    }

    private fun fetchBooksFromApi(index: Int, maxResults: Int): LiveData<Status> {
        responseLiveData.value = Status.Loading
        val call: Call<BookList>? = booksService.getBooks(index, maxResults)
        call?.enqueue(object : Callback<BookList> {
            override fun onFailure(call: Call<BookList>?, t: Throwable?) {
                t?.let {
                    responseLiveData.value = Status.Error(it)
                }
            }

            override fun onResponse(call: Call<BookList>?, response: Response<BookList>?) {
                response?.body()?.let {
                    responseLiveData.value = Status.Success(it)
                }
            }
        })
        return responseLiveData
    }
}