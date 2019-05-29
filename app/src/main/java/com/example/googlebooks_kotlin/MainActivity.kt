package com.example.googlebooks_kotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.googlebooks_kotlin.adapter.BookAdapter
import com.example.googlebooks_kotlin.api.BooksService
import com.example.googlebooks_kotlin.api.RetrofitClient
import com.example.googlebooks_kotlin.model.BookList
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    val MAX_RESULTS = 40

    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpOnScrollListener()
        loadBooks(0)
    }

    private fun setUpOnScrollListener() {
        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    var index = bookAdapter.indexCounter
                    loadBooks(index)
                }
            }
        }

        booksRecyclerView.addOnScrollListener(scrollListener)
    }

    private fun loadBooks(index: Int) {
        val service = RetrofitClient.getRetrofitInstance()?.create(BooksService::class.java)
        var call: Call<BookList>? = service?.getBooks(index, MAX_RESULTS)
        call?.enqueue(object : Callback<BookList> {
            override fun onFailure(call: Call<BookList>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "Error $t", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<BookList>?, response: Response<BookList>?) {
                if (index > 0) {
                    refreshData(response?.body())
                } else {
                    displayBooks(response?.body())
                }
            }

        })
    }

    private fun displayBooks(bookList: BookList?) {
        bookAdapter = BookAdapter(bookList)
        booksRecyclerView.layoutManager = GridLayoutManager(this, 3)
        booksRecyclerView.adapter = bookAdapter
    }

    private fun refreshData(bookList: BookList?) {
        bookAdapter.updateBookList(bookList!!)
    }
}
