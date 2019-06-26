package com.example.googlebooks_kotlin.utils

sealed class Status <T>{
    class Loading<T> : Status<T>()
    data class Error<T>(val throwable: Throwable) : Status<T>()
    data class Success<T>(val data: T) : Status<T>()
}