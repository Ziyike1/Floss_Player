package edu.temple.flossplayer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class BookPlayerFragment : Fragment() {

    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookViewModel = ViewModelProvider(requireActivity())[BookViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookViewModel.selectedIndex.observe(viewLifecycleOwner) { index ->
            bookViewModel.getSelectedBook()?.let { book ->
                displaySelectedBook(book)
            }
        }
    }

    fun displaySelectedBook(book: Book) {
        try {
            val bookFragment = childFragmentManager.findFragmentById(R.id.book_fragment_container) as? BookFragment
                ?: BookFragment().also {
                    childFragmentManager.beginTransaction()
                        .add(R.id.book_fragment_container, it)
                        .commit()
                }
            childFragmentManager.executePendingTransactions()
            bookFragment.displayBook(book)
        } catch (e: Exception) {
            Log.e("BookPlayerFragment", "Error displaying selected book", e)
        }
    }

}

