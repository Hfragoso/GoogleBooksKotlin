package com.example.googlebooks_kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.googlebooks_kotlin.R
import com.example.googlebooks_kotlin.model.BookList
import com.example.googlebooks_kotlin.model.Item
import com.squareup.picasso.Picasso

class BookAdapter(private var myBookList: BookList?) : RecyclerView.Adapter<BookAdapter.BooksViewHolder>() {

    var indexCounter = 1

    fun updateBookList(newCallData: BookList) {
        val mergeList = myBookList?.items
        mergeList?.addAll(newCallData.items)
        myBookList?.items = mergeList!!
        indexCounter++
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BooksViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myBookList?.items?.size!!
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(myBookList!!.items[position])
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

        fun bind(book: Item) {
            var imageUrl = book.volumeInfo.imageLinks?.thumbnail

            Picasso.get()
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(bookThumbnailImageView)

            bookTitleTextView?.text = book.volumeInfo.title
            bookPublishedDateTextView?.text = book.volumeInfo.publishedDate
            bookLayout?.setOnClickListener {
                Toast.makeText(itemView.context, "Clicked: ${book.id}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}