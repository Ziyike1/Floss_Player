package edu.temple.flossplayer

import Book
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookListAdapter(
    private var books: MutableList<Book>,
    private val onClick: (Book, Int) -> Unit // click listener lambda function
) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View, val onClick: (Book, Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        var titleView: TextView = itemView.findViewById(R.id.bookTitle)
        var authorView: TextView = itemView.findViewById(R.id.bookAuthor)
        private var currentBook: Book? = null
        private var currentPosition: Int = -1

        init {
            itemView.setOnClickListener {
                currentBook?.let {
                    onClick(it, adapterPosition)
                }
            }
        }

        fun bind(book: Book, position: Int) {
            currentBook = book
            currentPosition = position
            titleView.text = book.title
            authorView.text = book.author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BookViewHolder(itemView, onClick)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book, position)
    }

    override fun getItemCount() = books.size

    fun updateBooks(newBooks: List<Book>) {
        books.clear()
        books.addAll(newBooks)
        notifyDataSetChanged()
    }
}


