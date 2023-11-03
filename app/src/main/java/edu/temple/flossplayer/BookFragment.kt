package edu.temple.flossplayer

import Book
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class BookFragment : Fragment() {

    private lateinit var bookTitleTextView: TextView
    private lateinit var bookAuthorTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_book, container, false)
        bookTitleTextView = view.findViewById(R.id.bookTitle)
        bookAuthorTextView = view.findViewById(R.id.bookAuthor)
        return view
    }

    fun displayBook(book: Book) {
        if (isAdded) {
            bookTitleTextView.text = book.title
            bookAuthorTextView.text = book.author
        }
    }

}