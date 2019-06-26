package com.example.googlebooks_kotlin.screens.bookslanding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.googlebooks_kotlin.R
import com.example.googlebooks_kotlin.database.entities.BookEntity
import com.example.googlebooks_kotlin.utils.loadImage
import kotlinx.android.synthetic.main.book_item.view.*

class BooksLandingAdapter(
    private val onClickListener: (myBookList: List<BookEntity>, position: Int) -> Unit
) :
    RecyclerView.Adapter<BooksLandingAdapter.BooksViewHolder>() {

    companion object {
        const val EXTRA_SELECTED_POSITION: String = "com.example.googlebooks.EXTRA_SELECTED_POSITION"
        const val EXTRA_BOOK_LIST = "com.example.googlebooks.EXTRA_BOOK_LIST"
    }

    private var myBookList: MutableList<BookEntity> = mutableListOf()

    var indexCounter = 0

    fun updateBookList(data: MutableList<BookEntity>) {
        myBookList = data
        indexCounter++
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BooksViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myBookList.size
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(myBookList, position, onClickListener)
    }

    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            bookList: MutableList<BookEntity>,
            position: Int,
            onClickListener: (myBookList: List<BookEntity>, position: Int) -> Unit
        ) {
            val book = bookList[position]
            val imageUrl = book.thumbnail

            itemView.bookThumbnailImageView.loadImage(imageUrl)
            itemView.bookTitleTextView.text = book.title
            itemView.bookPublishedDateTextView.text = book.publishedDate
            itemView.bookLayout.setOnClickListener {
                onClickListener(bookList, position)
            }
        }
    }
}
