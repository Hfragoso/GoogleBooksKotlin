package com.example.googlebooks_kotlin.utils

import android.os.AsyncTask
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.googlebooks_kotlin.database.BookDao
import com.example.googlebooks_kotlin.entities.BookList
import com.example.googlebooks_kotlin.entities.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val booksService: BooksService,
    private val bookDao: BookDao
) {
    private val responseLiveData: MutableLiveData<Status> = MutableLiveData()
    val booksMediatorLiveData = MediatorLiveData<Status>()

    init {
        booksMediatorLiveData.addSource(responseLiveData) {
            booksMediatorLiveData.value = it
        }
    }

    fun fetchAllBooks(query: String, index: Int, maxResults: Int) {
        responseLiveData.value = Status.Loading
        fetchBooksFromApi(query, index, maxResults)
        fetchBooksQuery(query)
    }

    fun autoCompleteFromDB(query: String) {
        fetchBooksQuery(query)
    }

    private fun fetchBooksQuery(query: String) {
        val transformed = Transformations.switchMap(bookDao.getQuery("%$query%")) {
            responseLiveData.value = Status.Success(it)
            responseLiveData
        }
        booksMediatorLiveData.addSource(transformed) {
            booksMediatorLiveData.value = it
        }
    }

    private fun fetchBooksFromApi(query: String, index: Int, maxResults: Int) {
        val call: Call<BookList>? = booksService.getBooks(query, index, maxResults)
        call?.enqueue(object : Callback<BookList> {
            override fun onFailure(call: Call<BookList>?, t: Throwable?) {
                t?.let {
                    responseLiveData.value = Status.Error(it)
                }
            }

            override fun onResponse(call: Call<BookList>?, response: Response<BookList>?) {
                response?.body()?.items?.let {
                    InsertAsyncTask(
                        bookDao
                    ).execute(it)
                }
            }
        })
    }

    companion object {
        private class InsertAsyncTask(private val bookDao: BookDao) :
            AsyncTask<List<Item>, Void, Void>() {
            override fun doInBackground(vararg bookList: List<Item>?): Void? {
                val items = bookList.firstOrNull()?.toTypedArray()
                items?.let {
                    bookDao.insertList(*it)
                }

                return null
            }
        }
    }
}