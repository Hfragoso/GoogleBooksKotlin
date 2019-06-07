package com.example.googlebooks_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.googlebooks_kotlin.adapter.BookAdapter
import com.example.googlebooks_kotlin.dataModel.Status
import com.example.googlebooks_kotlin.di.AppComponent
import com.example.googlebooks_kotlin.di.AppModule
import com.example.googlebooks_kotlin.di.DaggerAppComponent
import com.example.googlebooks_kotlin.di.UtilsModule
import com.example.googlebooks_kotlin.model.BookList
import com.example.googlebooks_kotlin.model.Item
import com.example.googlebooks_kotlin.viewModel.BookListViewModel
import com.example.googlebooks_kotlin.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

private const val MAX_RESULTS = 40

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

//    TODO: Inject adapter under activity's scope

    private lateinit var bookAdapter: BookAdapter
    private lateinit var bookListViewModel: BookListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appComponent: AppComponent = initDagger()
        appComponent.doInjection(this)
        setUpOnScrollListener()
        bookListViewModel = ViewModelProviders.of(this, viewModelFactory)[BookListViewModel::class.java]
        fetchBooksLiveDataObserver(0)
    }

    private fun fetchBooksLiveDataObserver(index: Int) {
        bookListViewModel.fetchBooks(index, MAX_RESULTS).observe(this, Observer { status ->
            when (status) {
                is Status.Loading -> showProgressBar()
                is Status.Success -> handleBooks(status.data, index)
                is Status.Error -> showError(status.throwable)
            }
        })
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this@MainActivity, "Error: ${throwable.message}", Toast.LENGTH_LONG).show()
    }

    private fun handleBooks(data: BookList, index: Int) {
        if (index > 0) {
            refreshData(data)
        } else {
            displayBooks(data)
        }
    }

    private fun showProgressBar() {
        Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_LONG).show()
    }

    private fun setUpOnScrollListener() {
        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    val index = bookAdapter.indexCounter
                    fetchBooksLiveDataObserver(index)
                }
            }
        }

        booksRecyclerView.addOnScrollListener(scrollListener)
    }

    private fun displayBooks(bookList: BookList?) {
        bookAdapter = BookAdapter(bookList?.items as MutableList<Item>) { adapterBookList, position ->
            val intent = Intent(this@MainActivity, BookDetailsActivity::class.java)
            intent.putExtra(BookAdapter.EXTRA_SELECTED_POSITION, position)
            intent.putExtra(BookAdapter.EXTRA_BOOK_LIST, adapterBookList as ArrayList<Item>)
            startActivity(intent)
        }
        booksRecyclerView.layoutManager = GridLayoutManager(this, 3)
        booksRecyclerView.adapter = bookAdapter
    }

    private fun refreshData(bookList: BookList?) {
        bookAdapter.updateBookList(bookList?.items as MutableList<Item>)
    }

    private fun initDagger(): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .utilsModule(UtilsModule())
            .build()
}