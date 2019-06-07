package com.example.googlebooks_kotlin.bookdetails.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.googlebooks_kotlin.R
import com.example.googlebooks_kotlin.bookslanding.adapter.BooksAdapter
import com.example.googlebooks_kotlin.bookdetails.adapter.DetailsAdapter
import com.example.googlebooks_kotlin.entities.Item
import kotlinx.android.synthetic.main.activity_book_details.*

class BookDetailsActivity : AppCompatActivity() {

    private lateinit var detailsAdapter: DetailsAdapter
    private lateinit var bookList: List<Item>
    private var selectedBook: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)
        receiveIntent()
        displayDetails()
    }

    private fun displayDetails() {
        detailsAdapter =
            DetailsAdapter(bookList, this@BookDetailsActivity)
        viewPager.adapter = detailsAdapter
        viewPager.currentItem = selectedBook
    }

    private fun receiveIntent() {
        bookList = intent.getParcelableArrayListExtra(BooksAdapter.EXTRA_BOOK_LIST)
        selectedBook = intent.getIntExtra(BooksAdapter.EXTRA_SELECTED_POSITION, 0)
    }
}
