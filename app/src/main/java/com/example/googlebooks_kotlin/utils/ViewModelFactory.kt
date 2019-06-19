package com.example.googlebooks_kotlin.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.googlebooks_kotlin.screens.bookslanding.viewmodel.BookLandingViewModel
import javax.inject.Inject

class ViewModelFactory @Inject
constructor(private val repository: BooksRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BookLandingViewModel::class.java)) {
            BookLandingViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown class name")
        }
    }
}