package edu.temple.flossplayer

import Book
import BookList
import androidx.lifecycle.ViewModel

class BookViewModel : ViewModel() {
    private val bookList = BookList()

    fun getBooks() = bookList

    fun addBook(book: Book) {
        bookList.add(book)
    }

    fun getBook(index: Int): Book {
        return bookList.get(index)
    }
}
