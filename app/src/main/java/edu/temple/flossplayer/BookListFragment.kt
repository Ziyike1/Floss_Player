package edu.temple.flossplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var bookViewModel: BookViewModel
    private lateinit var adapter: BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get the ViewModel from the host activity
        bookViewModel = ViewModelProvider(requireActivity())[BookViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.bookListRecyclerView)
        // Use a linear layout manager
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = BookListAdapter(mutableListOf())
        recyclerView.adapter = adapter

        // Observe the book list from the ViewModel and update the adapter when it changes
        bookViewModel.bookList.observe(viewLifecycleOwner) { bookList ->
            // Your adapter needs to have a method to update the list data
            adapter.updateBooks(bookList.getBooks()) // Replace 'getBooks()' with the appropriate method to get the list from your 'BookList' class
        }
    }
}


