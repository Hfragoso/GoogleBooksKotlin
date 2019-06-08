package com.example.googlebooks_kotlin.di.modules

import android.content.Context
import android.content.Intent
import com.example.googlebooks_kotlin.bookdetails.view.BookDetailsActivity
import com.example.googlebooks_kotlin.bookslanding.adapter.BooksAdapter
import com.example.googlebooks_kotlin.entities.Item
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AdapterModule {
    @Singleton
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