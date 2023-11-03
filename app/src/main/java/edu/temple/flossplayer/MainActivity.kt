package edu.temple.flossplayer

import Book
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var bookViewModel: BookViewModel
    private var detailContainer: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        detailContainer = findViewById(R.id.container2)

        if (savedInstanceState == null) {
            if (detailContainer != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container1, BookListFragment())
                    .commit()

                supportFragmentManager.beginTransaction()
                    .replace(R.id.container2, BookPlayerFragment())
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, BookListFragment())
                    .commit()
            }
        }

        populateBookList()
        setupBookSelectionListener()
    }

    private fun setupBookSelectionListener() {
        bookViewModel.selectedIndex.observe(this) { index ->
            if (index != -1) {
                val selectedBook = bookViewModel.getSelectedBook()
                if (detailContainer != null) {
                    if (selectedBook != null) {
                        (supportFragmentManager.findFragmentById(R.id.container2) as? BookPlayerFragment)
                            ?.displaySelectedBook(selectedBook)
                    }
                } else {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, BookPlayerFragment())
                        .addToBackStack(null) // Allows user to reverse the operation
                        .commit()
                }
            }
        }
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

        runOnUiThread {
            myBooks.forEach { book ->
                bookViewModel.addBook(book)
            }
        }
    }
}
