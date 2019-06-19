package com.example.googlebooks_kotlin.screens.bookdetails.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.googlebooks_kotlin.R
import com.example.googlebooks_kotlin.entities.Item
import com.example.googlebooks_kotlin.screens.bookdetails.adapter.BookDetailsAdapter
import com.example.googlebooks_kotlin.screens.bookslanding.adapter.BooksLandingAdapter
import kotlinx.android.synthetic.main.activity_book_details.*

class BookDetailsActivity : AppCompatActivity() {

    private lateinit var bookDetailsAdapter: BookDetailsAdapter
    private lateinit var bookList: List<Item>
    private var selectedBook: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)
        receiveIntent()
        displayDetails()
    }

    private fun displayDetails() {
        bookDetailsAdapter =
            BookDetailsAdapter(
                bookList,
                this@BookDetailsActivity
            )
        viewPager.adapter = bookDetailsAdapter
        viewPager.currentItem = selectedBook
    }

    private fun receiveIntent() {
        bookList = intent.getParcelableArrayListExtra(BooksLandingAdapter.EXTRA_BOOK_LIST)
        selectedBook = intent.getIntExtra(BooksLandingAdapter.EXTRA_SELECTED_POSITION, 0)
    }
}
