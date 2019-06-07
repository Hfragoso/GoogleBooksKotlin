package com.example.googlebooks_kotlin.bookslanding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.googlebooks_kotlin.bookslanding.datamodel.BooksRepository
import com.example.googlebooks_kotlin.utils.Status
import javax.inject.Inject


class BookListViewModel @Inject constructor(private val repository: BooksRepository) : ViewModel() {

    fun fetchBooks(index: Int, maxResults: Int): LiveData<Status> {
        return repository.fetchBooks(index, maxResults)
    }
}