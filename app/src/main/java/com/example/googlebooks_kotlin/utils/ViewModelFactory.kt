package com.example.googlebooks_kotlin.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.googlebooks_kotlin.bookslanding.viewmodel.BookListViewModel
import com.example.googlebooks_kotlin.bookslanding.datamodel.BooksRepository
import javax.inject.Inject

class ViewModelFactory @Inject
constructor(private val repository: BooksRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BookListViewModel::class.java)) {
            BookListViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown class name")
        }
    }
}