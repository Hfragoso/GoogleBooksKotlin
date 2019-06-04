package com.example.googlebooks_kotlin.dataModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.googlebooks_kotlin.api.BooksService
import com.example.googlebooks_kotlin.api.RetrofitClient
import com.example.googlebooks_kotlin.model.BookList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksRepository {
    val responseLiveData: MutableLiveData<BookList>? = null

    fun fetchBooks(index: Int, maxResults: Int): LiveData<BookList>? {
        return fetchBooksFromApi(index, maxResults)
    }

    private fun fetchBooksFromApi(index: Int, maxResults: Int): LiveData<BookList>? {
        val service = RetrofitClient.getRetrofitInstance()?.create(BooksService::class.java)
        val call: Call<BookList>? = service?.getBooks(index, maxResults)
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