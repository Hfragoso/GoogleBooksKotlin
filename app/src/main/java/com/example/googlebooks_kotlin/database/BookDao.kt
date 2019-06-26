package com.example.googlebooks_kotlin.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.googlebooks_kotlin.database.entities.AuthorEntity
import com.example.googlebooks_kotlin.database.entities.BookAuthorEntity
import com.example.googlebooks_kotlin.database.entities.BookEntity
import com.example.googlebooks_kotlin.models.Item

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: BookEntity)

    @Insert
    fun insertAuthor(author: AuthorEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(vararg book: BookEntity)

    @Insert
    fun insertAuthors(vararg author: AuthorEntity): List<Long>

    @Insert
    fun insertBookAuthorList(vararg bookAuthor: BookAuthorEntity)

    @Query("SELECT * FROM book_table WHERE book_title LIKE :query")
    fun getBooks(query: String): LiveData<List<BookEntity>>

    @Query("SELECT * FROM book_author_table WHERE book_id = :bookId")
    fun getBookAuthors(bookId: String): LiveData<List<BookAuthorEntity>>

    @Query("SELECT * FROM AUTHOR_TABLE WHERE author_id IN (:idList)")
    fun getAuthorsName(idList: List<Int>): LiveData<List<AuthorEntity>>

    @Query("SELECT * FROM author_table WHERE author_id IN (SELECT author_id FROM book_author_table WHERE book_id = :bookId)")
    fun getAuthorsByBookId(bookId: String): LiveData<List<AuthorEntity>>

    @get:Query("SELECT * FROM book_author_table")
    val allBooks: LiveData<List<Item>>
}