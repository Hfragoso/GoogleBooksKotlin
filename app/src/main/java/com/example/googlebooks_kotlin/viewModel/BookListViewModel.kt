package com.example.googlebooks_kotlin.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.googlebooks_kotlin.dataModel.BooksRepository
import com.example.googlebooks_kotlin.model.BookList

class BookListViewModel : ViewModel() {
    private val repository = BooksRepository()
//    TODO: Move index logic to ViewModel

    fun fetchBooks(index: Int, maxResults: Int): LiveData<BookList>? {
        return repository.fetchBooks(index, maxResults)
    }
}