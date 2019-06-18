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
import com.example.googlebooks_kotlin.entities.Item
import com.example.googlebooks_kotlin.utils.App
import com.example.googlebooks_kotlin.utils.Status
import com.example.googlebooks_kotlin.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

const val MAX_RESULTS = 40

class BooksLandingActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var booksAdapter: BooksAdapter

    private lateinit var bookListViewModel: BookListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val component = App.getApplication(this).component
        component.doInjection(this)
        booksAdapter = component
            .adapterSubcomponentBuilder()
            .build()
            .buildAdapter()
        setUpOnScrollListener()
        bookListViewModel = ViewModelProviders.of(this, viewModelFactory)[BookListViewModel::class.java]
        setuUpSearchView()
        setUpBooksObserver()
        setUpRecyclerView()
    }

    private fun setuUpSearchView() {
        search_bar.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    bookListViewModel.fetchBooks(query, 0, MAX_RESULTS)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun setUpBooksObserver() {
        bookListViewModel.booksData.observe(this, Observer { status ->
            when (status) {
                is Status.Loading -> showProgressBar()
                is Status.Success -> handleBooks(status.data)
                is Status.Error -> showError(status.throwable)
            }
        })
    }

    private fun setUpRecyclerView() {
        booksRecyclerView.layoutManager = GridLayoutManager(this, 3)
        booksRecyclerView.adapter = booksAdapter
    }

    private fun fetchBooksLiveDataObserver(index: Int) {
        bookListViewModel.fetchBooks(search_bar.query.toString(), index, MAX_RESULTS)
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this@BooksLandingActivity, "Error: ${throwable.message}", Toast.LENGTH_LONG).show()
    }

    private fun handleBooks(data: List<Item>) {
        setAdapterData(data)
    }

    private fun showProgressBar() {
        Toast.makeText(this@BooksLandingActivity, "Loading", Toast.LENGTH_LONG).show()
    }

    private fun setUpOnScrollListener() {
        val scrollListener = object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_INDICATOR_BOTTOM) {
                    val index = booksAdapter.indexCounter
                    fetchBooksLiveDataObserver(index)
                }

            }
        }

        booksRecyclerView.addOnScrollListener(scrollListener)
    }

    private fun setAdapterData(data: List<Item>) {
        booksAdapter.updateBookList(data as MutableList<Item>)
    }
}