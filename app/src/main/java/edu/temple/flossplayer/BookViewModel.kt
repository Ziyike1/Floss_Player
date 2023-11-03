package edu.temple.flossplayer

import Book
import BookList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookViewModel : ViewModel() {

    private val _bookList = MutableLiveData<BookList>().apply { value = BookList() }
    val bookList: LiveData<BookList>
        get() = _bookList

    private val _selectedIndex = MutableLiveData<Int>()
    val selectedIndex: LiveData<Int>
        get() = _selectedIndex

    fun addBook(book: Book) {
        _bookList.value?.add(book)
        _bookList.postValue(_bookList.value)
    }

    fun setSelectedIndex(index: Int) {
        _selectedIndex.value = index
    }

    fun getSelectedBook(): Book? {
        return _bookList.value?.get(_selectedIndex.value ?: -1)
    }
}

