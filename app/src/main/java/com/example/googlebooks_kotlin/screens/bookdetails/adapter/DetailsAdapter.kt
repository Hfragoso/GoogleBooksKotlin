package com.example.googlebooks_kotlin.screens.bookdetails.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.googlebooks_kotlin.R
import com.example.googlebooks_kotlin.entities.Item
import com.example.googlebooks_kotlin.utils.loadImage
import kotlinx.android.synthetic.main.book_detail_page.view.*

class DetailsAdapter(private val bookList: List<Item>, private val context: Context) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return bookList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.book_detail_page, container, false) as ViewGroup
        val volumeInfo = bookList[position].volumeInfo
        val imageUrl = volumeInfo?.imageLinks?.thumbnail

        layout.bookCover.loadImage(imageUrl)
        layout.bookPublishedDate.text = volumeInfo?.publishedDate
        layout.authors.text = formatAuthors(volumeInfo?.authors)
        layout.description.text = volumeInfo?.description

        container.addView(layout)

        return layout
    }

    private fun formatAuthors(authors: List<String>?): String {
        var result = ""
        for (author in authors ?: emptyList()) {
            result = result.plus("$author, ")
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