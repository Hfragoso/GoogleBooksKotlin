package com.example.googlebooks_kotlin.screens.bookslanding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.googlebooks_kotlin.screens.bookslanding.datamodel.BooksRepository
import com.example.googlebooks_kotlin.utils.Status
import javax.inject.Inject


class BookListViewModel @Inject constructor(private val repository: BooksRepository) : ViewModel() {
    var booksData: LiveData<Status> = repository.responseLiveData

    fun fetchBooks(query: String, index: Int, maxResults: Int) {
        repository.fetchBooks(query, index, maxResults)
    }
}