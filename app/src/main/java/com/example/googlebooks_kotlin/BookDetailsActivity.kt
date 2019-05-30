package com.example.googlebooks_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.googlebooks_kotlin.adapter.BookAdapter
import com.example.googlebooks_kotlin.adapter.BookViewPagerAdapter
import com.example.googlebooks_kotlin.model.Item
import kotlinx.android.synthetic.main.activity_book_details.*

class BookDetailsActivity : AppCompatActivity() {

    private lateinit var bookViewPagerAdapter: BookViewPagerAdapter
    private lateinit var bookList: List<Item>
    var selectedBook: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)
        receiveIntent()
        displayDetails()
    }

    private fun displayDetails() {
        bookViewPagerAdapter = BookViewPagerAdapter(bookList, this@BookDetailsActivity)
        viewPager.adapter = bookViewPagerAdapter
        viewPager.currentItem = selectedBook
    }

    private fun receiveIntent() {
        bookList = intent.getSerializableExtra(BookAdapter.EXTRA_BOOK_LIST) as List<Item>
        selectedBook = intent.getIntExtra(BookAdapter.EXTRA_SELECTED_POSITION, 0)
    }
}
