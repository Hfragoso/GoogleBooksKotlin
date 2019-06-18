package com.example.googlebooks_kotlin.di.modules

import android.content.Context
import android.content.Intent
import com.example.googlebooks_kotlin.di.scopes.Activity
import com.example.googlebooks_kotlin.entities.Item
import com.example.googlebooks_kotlin.screens.bookdetails.view.BookDetailsActivity
import com.example.googlebooks_kotlin.screens.bookslanding.adapter.BooksAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {
    @Activity
    @Provides
    fun provideAdapter(context: Context): BooksAdapter {
        return BooksAdapter { adapterBookList, position ->
            val intent = Intent(context, BookDetailsActivity::class.java)
            intent.putExtra(
                BooksAdapter.EXTRA_SELECTED_POSITION,
                position
            )
            intent.putExtra(
                BooksAdapter.EXTRA_BOOK_LIST,
                adapterBookList as ArrayList<Item>
            )
            context.startActivity(intent)
        }
    }
}