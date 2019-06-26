package com.example.googlebooks_kotlin.screens.bookdetails.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.googlebooks_kotlin.R
import com.example.googlebooks_kotlin.database.entities.AuthorEntity
import com.example.googlebooks_kotlin.database.entities.BookEntity
import com.example.googlebooks_kotlin.utils.loadImage
import kotlinx.android.synthetic.main.book_detail_page.view.*

class BookDetailsAdapter(
    private val bookList: List<BookEntity>,
    private val context: Context,
    private val getAuthors: (bookId: String) -> Unit
) : PagerAdapter() {
    private var authorList: MutableList<AuthorEntity> = mutableListOf()

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return bookList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        getAuthors(bookList[position].id)
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.book_detail_page, container, false) as ViewGroup
        val imageUrl = bookList[position].thumbnail

        layout.bookCover.loadImage(imageUrl)
        layout.bookPublishedDate.text = bookList[position].publishedDate
//        layout.authors.text = formatAuthors(bookList[position].authors)
        layout.authors.text = formatAuthors(authorList)
        layout.description.text = bookList[position].description

        container.addView(layout)

        return layout
    }

    fun updateAuthors(newAuthors: MutableList<AuthorEntity>) {
        authorList = newAuthors
        notifyDataSetChanged()
    }

    private fun formatAuthors(authors: List<AuthorEntity>?): String {
        var result = ""
        for (author in authors ?: emptyList()) {
            result = result.plus("${author.name}, ")
        }

        return if (result.isNotEmpty())
            result.substring(0, result.length - 2)
        else
            ""
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}