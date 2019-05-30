package com.example.googlebooks_kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.googlebooks_kotlin.R
import com.example.googlebooks_kotlin.model.Item
import com.squareup.picasso.Picasso

class BookViewPagerAdapter(private val bookList: List<Item>, private val context: Context) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return bookList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.book_detail_page, container, false) as ViewGroup
        val bookCover: ImageView = layout.findViewById(R.id.bookCover)
        val bookPublishedDate: TextView = layout.findViewById(R.id.bookPublishedDate)
        val authors: TextView = layout.findViewById(R.id.authors)
        val description: TextView = layout.findViewById(R.id.description)


        val volumeInfo = bookList[position].volumeInfo
        val imageUrl = volumeInfo?.imageLinks?.thumbnail

        Picasso.get()
            .load(imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(bookCover)

        bookPublishedDate.text = volumeInfo?.publishedDate
        authors.text = formatAuthors(volumeInfo?.authors)
        description.text = volumeInfo?.description

        container.addView(layout)

        return layout
    }

    private fun formatAuthors(authors: List<String>?): String {
        var result = ""
        for (author in authors ?: emptyList()) {
            result = result.plus("$author, ")
        }

        return result.substring(0, result.length - 2)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}