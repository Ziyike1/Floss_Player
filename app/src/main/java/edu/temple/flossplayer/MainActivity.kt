package edu.temple.flossplayer

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var bookViewModel: BookViewModel
    private var isTwoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        val detailContainer: FrameLayout? = findViewById(R.id.container2)
        isTwoPane = detailContainer != null

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BookListFragment())
                .commit()

            if (isTwoPane) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container2, BookPlayerFragment())
                    .commit()
            }
        }

        populateBookList()
        setupBookSelectionListener()
    }

    private fun setupBookSelectionListener() {
        try {
            bookViewModel.selectedIndex.observe(this) { index ->
                if (index != -1) {
                    val selectedBook = bookViewModel.getSelectedBook()
                    if (selectedBook != null) {
                        if (isTwoPane) {
                            (supportFragmentManager.findFragmentById(R.id.container2) as? BookPlayerFragment)
                                ?.displaySelectedBook(selectedBook)
                        } else {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.container, BookPlayerFragment().apply {
                                    arguments = Bundle().apply {
                                        putSerializable("selected_book", selectedBook)
                                    }
                                })
                                .addToBackStack(null) // Allows user to reverse the operation
                                .commit()
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Error setting up book selection listener", e)
        }
    }

    private fun populateBookList() {
        try {
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
                try {
                    myBooks.forEach { book ->
                        bookViewModel.addBook(book)
                    }
                } catch (e: Exception) {
                    Log.e("MainActivity", "Error populating book list", e)
                }
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Error setting up book list", e)
        }
    }
}
