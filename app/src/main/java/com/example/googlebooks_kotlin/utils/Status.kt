package com.example.googlebooks_kotlin.utils

import com.example.googlebooks_kotlin.entities.Item

sealed class Status {
    object Loading : Status()
    data class Error(val throwable: Throwable) : Status()
    data class Success(val data: List<Item>) : Status()
}