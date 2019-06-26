package com.example.googlebooks_kotlin.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.googlebooks_kotlin.database.BookDao
import com.example.googlebooks_kotlin.database.entities.AuthorEntity
import com.example.googlebooks_kotlin.database.entities.BookAuthorEntity
import com.example.googlebooks_kotlin.database.entities.BookEntity
import com.example.googlebooks_kotlin.models.Item
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val booksService: BooksService,
    private val bookDao: BookDao
) {
    private var disposable = CompositeDisposable()

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
        disposable.add(booksService.getBooks(query, index, maxResults)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { statusMediatorLiveData.value = Status.Loading() }
            .subscribe({
                it.items?.let { itemList ->
                    itemList.forEach { book ->
                        val authorsId = bookDao.insertAuthors(*getAuthorsList(book))
                        bookDao.insertBook(getBookEntityFromItem(book))
                        val booksAuthors = mutableListOf<BookAuthorEntity>()
                        authorsId.forEach { authorId ->
                            val bookAuthor = BookAuthorEntity(0, book.id, authorId.toInt())
                            booksAuthors.add(bookAuthor)
                        }
                        bookDao.insertBookAuthorList(*booksAuthors.toTypedArray())
                    }
                }
            }, { throwable -> statusMediatorLiveData.value = Status.Error(throwable) })
        )
    }

    fun fetchAuthorsByBook(bookId: String): LiveData<List<AuthorEntity>> {
        return bookDao.getAuthorsByBookId(bookId)
    }

    fun clearDisposable() {
        disposable.clear()
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
    }
}