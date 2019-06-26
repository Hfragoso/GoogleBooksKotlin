package com.example.googlebooks_kotlin.screens.bookdetails.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.googlebooks_kotlin.R
import com.example.googlebooks_kotlin.database.entities.BookEntity
import com.example.googlebooks_kotlin.screens.bookdetails.adapter.BookDetailsAdapter
import com.example.googlebooks_kotlin.screens.bookdetails.viewmodel.BookDetailsViewModel
import com.example.googlebooks_kotlin.screens.bookslanding.adapter.BooksLandingAdapter
import com.example.googlebooks_kotlin.utils.App
import com.example.googlebooks_kotlin.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_book_details.*
import javax.inject.Inject

class BookDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var bookDetailsViewModel: BookDetailsViewModel

    private lateinit var bookDetailsAdapter: BookDetailsAdapter
    private lateinit var bookList: List<BookEntity>
    private var selectedBook: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)
        val component = App.getApplication(this).component
        component.doInjection(this)

        bookDetailsViewModel = ViewModelProviders.of(this, viewModelFactory)[BookDetailsViewModel::class.java]
        receiveIntent()
        displayDetails()
    }

    private fun displayDetails() {
        bookDetailsAdapter =
            BookDetailsAdapter(
                bookList,
                this@BookDetailsActivity
            ) { bookId ->
                bookDetailsViewModel.fetchAuthorsByBook(bookId).observe(this, Observer {
                    bookDetailsAdapter.updateAuthors(it.toMutableList())
                })
            }
        viewPager.adapter = bookDetailsAdapter
        viewPager.currentItem = selectedBook
    }

    private fun receiveIntent() {
        bookList = intent.getParcelableArrayListExtra(BooksLandingAdapter.EXTRA_BOOK_LIST)
        selectedBook = intent.getIntExtra(BooksLandingAdapter.EXTRA_SELECTED_POSITION, 0)
    }
}
