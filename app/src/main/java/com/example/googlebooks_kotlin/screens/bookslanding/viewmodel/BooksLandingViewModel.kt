package com.example.googlebooks_kotlin.screens.bookslanding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.googlebooks_kotlin.database.entities.BookEntity
import com.example.googlebooks_kotlin.utils.BooksRepository
import com.example.googlebooks_kotlin.utils.Status
import javax.inject.Inject


class BooksLandingViewModel @Inject constructor(private val repository: BooksRepository) : ViewModel() {
    var booksData: LiveData<Status<List<BookEntity>>> = repository.statusMediatorLiveData

    fun fetchAllBooks(query: String, index: Int, maxResults: Int) {
        repository.fetchAllBooks(query, index, maxResults)
    }

    fun autoCompleteFromDB(query: String) {
        repository.autoCompleteFromDB(query)
    }

    override fun onCleared() {
        super.onCleared()
        repository.clearDisposable()
    }
}