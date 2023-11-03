package edu.temple.flossplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.res.Configuration
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
        bookViewModel = ViewModelProvider(requireActivity())[BookViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.bookListRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = BookListAdapter(bookViewModel.bookList.value?.getBooks()?.toMutableList() ?: mutableListOf()) { book, position ->
            bookViewModel.setSelectedIndex(position)

            if (!isInLandscapeMode()) {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, BookPlayerFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
        recyclerView.adapter = adapter

        bookViewModel.bookList.observe(viewLifecycleOwner) { bookList ->
            adapter.updateBooks(bookList.getBooks().toMutableList())
        }
    }

    private fun isInLandscapeMode(): Boolean {
        val displayMode = resources.configuration.orientation
        return displayMode == Configuration.ORIENTATION_LANDSCAPE
    }
}



