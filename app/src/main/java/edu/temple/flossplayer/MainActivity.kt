package edu.temple.flossplayer

import Book
import BookList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var bookList: BookList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookList = BookList()

        populateBookList()
    }

    private fun populateBookList() {
        val myBooks = arrayOf(
            Book("The Great Gatsby", "F. Scott Fitzgerald"),
            Book("1984", "George Orwell"),
            Book("To Kill a Mockingbird", "Harper Lee"),
            Book("Brave New World", "Aldous Huxley"),
            Book("The Catcher in the Rye", "J.D. Salinger"),
            Book("The Lord of the Rings", "J.R.R. Tolkien"),
            Book("Pride and Prejudice", "Jane Austen"),
            Book("The Grapes of Wrath", "John Steinbeck"),
            Book("The Chronicles of Narnia", "C.S. Lewis"),
            Book("Animal Farm", "George Orwell")
        )

        myBooks.forEach { bookList.add(it) }
    }
}
