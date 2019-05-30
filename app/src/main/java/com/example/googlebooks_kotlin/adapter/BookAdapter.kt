package com.example.googlebooks_kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.googlebooks_kotlin.R
import com.example.googlebooks_kotlin.model.Item
import com.squareup.picasso.Picasso

class BookAdapter(
    private var myBookList: MutableList<Item>,
    private val onClickListener: (myBookList: List<Item>, position: Int) -> Unit
) :
    RecyclerView.Adapter<BookAdapter.BooksViewHolder>() {

    companion object {
        const val EXTRA_SELECTED_POSITION: String = "com.example.googlebooks.EXTRA_SELECTED_POSITION"
        const val EXTRA_BOOK_LIST = "com.example.googlebooks.EXTRA_BOOK_LIST"
    }

    var indexCounter = 1

    fun updateBookList(newCallData: MutableList<Item>) {
        val mergeList = myBookList
        mergeList.addAll(newCallData)
        myBookList = mergeList
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
        private var bookThumbnailImageView: ImageView? = null
        private var bookTitleTextView: TextView? = null
        private var bookPublishedDateTextView: TextView? = null
        private var bookLayout: LinearLayout? = null

        init {
            bookThumbnailImageView = itemView.findViewById(R.id.bookThumbnailImageView)
            bookTitleTextView = itemView.findViewById(R.id.bookTitleTextView)
            bookPublishedDateTextView = itemView.findViewById(R.id.bookPublishedDateTextView)
            bookLayout = itemView.findViewById(R.id.bookLayout)
        }

        fun bind(
            bookList: MutableList<Item>,
            position: Int,
            onClickListener: (myBookList: List<Item>, position: Int) -> Unit
        ) {
            val book = bookList[position]
            val imageUrl = book.volumeInfo?.imageLinks?.thumbnail
            //TODO: Convert to extension function(Add extension function to ImageView
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(bookThumbnailImageView)

            bookTitleTextView?.text = book.volumeInfo?.title
            bookPublishedDateTextView?.text = book.volumeInfo?.publishedDate
            bookLayout?.setOnClickListener {
                onClickListener(bookList, position)
            }
        }
    }
}