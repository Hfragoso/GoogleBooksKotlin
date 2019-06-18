package com.example.googlebooks_kotlin.screens.bookslanding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.googlebooks_kotlin.R
import com.example.googlebooks_kotlin.entities.Item
import com.example.googlebooks_kotlin.utils.loadImage
import kotlinx.android.synthetic.main.book_item.view.*

class BooksAdapter(
    private val onClickListener: (myBookList: List<Item>, position: Int) -> Unit
) :
    RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    companion object {
        const val EXTRA_SELECTED_POSITION: String = "com.example.googlebooks.EXTRA_SELECTED_POSITION"
        const val EXTRA_BOOK_LIST = "com.example.googlebooks.EXTRA_BOOK_LIST"
    }

    private var myBookList: MutableList<Item> = mutableListOf()

    var indexCounter = 0

    fun updateBookList(data: MutableList<Item>) {
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
            bookList: MutableList<Item>,
            position: Int,
            onClickListener: (myBookList: List<Item>, position: Int) -> Unit
        ) {
            val book = bookList[position]
            val imageUrl = book.volumeInfo?.imageLinks?.thumbnail

            itemView.bookThumbnailImageView.loadImage(imageUrl)
            itemView.bookTitleTextView.text = book.volumeInfo?.title
            itemView.bookPublishedDateTextView.text = book.volumeInfo?.publishedDate
            itemView.bookLayout.setOnClickListener {
                onClickListener(bookList, position)
            }
        }
    }
}
