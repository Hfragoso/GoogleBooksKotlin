package com.example.googlebooks_kotlin.utils

sealed class Status {
    object Loading : Status()
    data class Error(val throwable: Throwable) : Status()
    data class Success<T>(val data: T) : Status()
}