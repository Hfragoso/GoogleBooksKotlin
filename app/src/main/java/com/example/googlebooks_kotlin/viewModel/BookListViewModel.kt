package com.example.googlebooks_kotlin.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.googlebooks_kotlin.dataModel.BooksRepository
import com.example.googlebooks_kotlin.model.BookList
import javax.inject.Inject


class BookListViewModel @Inject constructor(repository: BooksRepository) : ViewModel() {
//class BookListViewModel : ViewModel() {
    //    TODO: Move index logic to ViewModel

    private val repository = repository

    fun fetchBooks(index: Int, maxResults: Int): LiveData<BookList>? {
        return repository.fetchBooks(index, maxResults)
    }
}