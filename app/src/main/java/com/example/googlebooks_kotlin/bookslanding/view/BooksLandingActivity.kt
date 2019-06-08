package com.example.googlebooks_kotlin.bookslanding.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.googlebooks_kotlin.R
import com.example.googlebooks_kotlin.bookslanding.adapter.BooksAdapter
import com.example.googlebooks_kotlin.bookslanding.viewmodel.BookListViewModel
import com.example.googlebooks_kotlin.di.modules.ContextModule
import com.example.googlebooks_kotlin.entities.BookList
import com.example.googlebooks_kotlin.entities.Item
import com.example.googlebooks_kotlin.utils.App
import com.example.googlebooks_kotlin.utils.Status
import com.example.googlebooks_kotlin.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

private const val MAX_RESULTS = 40

class BooksLandingActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var booksAdapter: BooksAdapter
//    TODO: Inject adapter under activity's scope

    private lateinit var bookListViewModel: BookListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var component = App.getApplication(this).component
        component.doInjection(this)
//        component.adapterSubcomponentBuilder()
//            .ContextModule(ContextModule(application))
//            .build()
        setUpOnScrollListener()
        bookListViewModel = ViewModelProviders.of(this, viewModelFactory)[BookListViewModel::class.java]
        setUpRecyclerView()
        fetchBooksLiveDataObserver(0)
    }

    private fun setUpRecyclerView() {
        booksRecyclerView.layoutManager = GridLayoutManager(this, 3)
        booksRecyclerView.adapter = booksAdapter
    }

    private fun fetchBooksLiveDataObserver(index: Int) {
        bookListViewModel.fetchBooks(index, MAX_RESULTS).observe(this, Observer { status ->
            when (status) {
                is Status.Loading -> showProgressBar()
                is Status.Success -> handleBooks(status.data)
                is Status.Error -> showError(status.throwable)
            }
        })
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this@BooksLandingActivity, "Error: ${throwable.message}", Toast.LENGTH_LONG).show()
    }

    private fun handleBooks(data: BookList) {
        setData(data)
    }

    private fun showProgressBar() {
        Toast.makeText(this@BooksLandingActivity, "Loading", Toast.LENGTH_LONG).show()
    }

    private fun setUpOnScrollListener() {
        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    val index = booksAdapter.indexCounter
                    fetchBooksLiveDataObserver(index)
                }
            }
        }

        booksRecyclerView.addOnScrollListener(scrollListener)
    }

    private fun setData(bookList: BookList?) {
        booksAdapter.updateBookList(bookList?.items as MutableList<Item>)
    }
}