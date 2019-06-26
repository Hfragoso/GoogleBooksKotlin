package com.example.googlebooks_kotlin.screens.bookdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.googlebooks_kotlin.database.entities.AuthorEntity
import com.example.googlebooks_kotlin.utils.BooksRepository
import javax.inject.Inject

class BookDetailsViewModel @Inject constructor(private val repository: BooksRepository) : ViewModel() {
    fun fetchAuthorsByBook(bookId: String): LiveData<List<AuthorEntity>> {
        return repository.fetchAuthorsByBook(bookId)
    }
}