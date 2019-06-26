package com.example.googlebooks_kotlin.utils

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.googlebooks_kotlin.database.BookDao
import com.example.googlebooks_kotlin.database.entities.AuthorEntity
import com.example.googlebooks_kotlin.database.entities.BookAuthorEntity
import com.example.googlebooks_kotlin.database.entities.BookEntity
import com.example.googlebooks_kotlin.models.BookList
import com.example.googlebooks_kotlin.models.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val booksService: BooksService,
    private val bookDao: BookDao
) {
    private val queryLiveData: MutableLiveData<String> = MutableLiveData()
    private val bookListLiveData: LiveData<List<BookEntity>> = Transformations.switchMap(queryLiveData) { query ->
        Transformations.map(bookDao.getBooks("%$query%")) { it }
    }
    val statusMediatorLiveData = MediatorLiveData<Status<List<BookEntity>>>()

    init {
        statusMediatorLiveData.addSource(bookListLiveData) {
            statusMediatorLiveData.value = Status.Success(it)
        }
    }

    fun fetchAllBooks(query: String, index: Int, maxResults: Int) {
        statusMediatorLiveData.value = Status.Loading()
        fetchBooksFromApi(query, index, maxResults)
        fetchBooksQuery(query)
    }

    fun autoCompleteFromDB(query: String) {
        fetchBooksQuery(query)
    }

    private fun fetchBooksQuery(query: String) {
        queryLiveData.value = query
    }

    private fun fetchBooksFromApi(query: String, index: Int, maxResults: Int) {
        val call: Call<BookList>? = booksService.getBooks(query, index, maxResults)
        call?.enqueue(object : Callback<BookList> {
            override fun onFailure(call: Call<BookList>?, t: Throwable?) {
                t?.let {
                    statusMediatorLiveData.value = Status.Error(it)
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

    fun fetchAuthorsByBook(bookId: String): LiveData<List<AuthorEntity>> {
        return bookDao.getAuthorsByBookId(bookId)
    }

    companion object {
        fun getBookEntityFromItem(item: Item): BookEntity {
            return BookEntity(
                item.id,
                item.volumeInfo?.title,
                item.volumeInfo?.description,
                item.volumeInfo?.publishedDate,
                item.volumeInfo?.imageLinks?.thumbnail
            )
        }

        fun getAuthorsList(bookList: Item): Array<AuthorEntity> {
            val authors = mutableListOf<AuthorEntity>()

            bookList.volumeInfo?.authors.let { authorList ->
                authorList?.forEach {
                    val author = AuthorEntity(0, it)
                    authors.add(author)
                }
            }

            return authors.toTypedArray()
        }

        private class InsertAsyncTask(private val bookDao: BookDao) :
            AsyncTask<List<Item>, Void, Void>() {
            override fun doInBackground(vararg bookList: List<Item>?): Void? {
                val items = bookList.firstOrNull()?.toTypedArray()
                items?.let { itemList ->

                    itemList.forEach { book ->
                        val authorsId = bookDao.insertAuthors(*getAuthorsList(book))
                        bookDao.insertBook(getBookEntityFromItem(book))
                        val booksAuthors = mutableListOf<BookAuthorEntity>()
                        authorsId.forEach {
                            val bookAuthor = BookAuthorEntity(0, book.id, it.toInt())
                            booksAuthors.add(bookAuthor)
                        }
                        bookDao.insertBookAuthorList(*booksAuthors.toTypedArray())
                    }
                }

                return null
            }
        }
    }
}