package com.example.googlebooks_kotlin.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.googlebooks_kotlin.dataModel.BooksRepository
import com.example.googlebooks_kotlin.dataModel.Status
import javax.inject.Inject


class BookListViewModel @Inject constructor(private val repository: BooksRepository) : ViewModel() {

    fun fetchBooks(index: Int, maxResults: Int): LiveData<Status> {
        return repository.fetchBooks(index, maxResults)
    }
}