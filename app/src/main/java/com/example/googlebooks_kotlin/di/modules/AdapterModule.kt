package com.example.googlebooks_kotlin.di.modules

import android.content.Context
import android.content.Intent
import com.example.googlebooks_kotlin.database.entities.BookEntity
import com.example.googlebooks_kotlin.di.scopes.Activity
import com.example.googlebooks_kotlin.screens.bookdetails.view.BookDetailsActivity
import com.example.googlebooks_kotlin.screens.bookslanding.adapter.BooksLandingAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {
    @Activity
    @Provides
    fun provideAdapter(context: Context): BooksLandingAdapter {
        return BooksLandingAdapter { adapterBookList, position ->
            val intent = Intent(context, BookDetailsActivity::class.java)
            intent.putExtra(
                BooksLandingAdapter.EXTRA_SELECTED_POSITION,
                position
            )
            intent.putExtra(
                BooksLandingAdapter.EXTRA_BOOK_LIST,
                adapterBookList as ArrayList<BookEntity>
            )
            context.startActivity(intent)
        }
    }
}