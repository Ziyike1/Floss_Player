package edu.temple.flossplayer

import Book
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtain the ViewModel - ViewModelProviders is deprecated so we use ViewModelProvider
        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BookListFragment())
                .commitNow()
        }

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

        // Since we are directly interacting with the ViewModel, we must do so on the main thread
        runOnUiThread {
            myBooks.forEach { book ->
                bookViewModel.addBook(book)
            }
        }
    }
}
