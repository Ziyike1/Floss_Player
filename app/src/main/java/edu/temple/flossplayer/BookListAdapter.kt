package edu.temple.flossplayer

import Book
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookListAdapter(private var books: MutableList<Book>) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    fun updateBooks(newBooks: List<Book>) {
        books.clear()
        books.addAll(newBooks)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.titleView.text = book.title
        holder.authorView.text = book.author
    }

    override fun getItemCount() = books.size
    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleView: TextView = itemView.findViewById(R.id.bookTitle)
        var authorView: TextView = itemView.findViewById(R.id.bookAuthor)
    }
}

