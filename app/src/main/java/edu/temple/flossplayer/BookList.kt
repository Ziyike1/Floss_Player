class BookList {
    private val books: MutableList<Book> = mutableListOf()

    fun add(book: Book) {
        books.add(book)
    }

    fun get(index: Int): Book {
        if (index in books.indices) {
            return books[index]
        } else {
            throw IndexOutOfBoundsException("Index: $index, Size: ${books.size}")
        }
    }

    fun remove(book: Book) {
        books.remove(book)
    }

    fun size(): Int = books.size

    override fun toString(): String {
        return books.joinToString(separator = "\n") { book ->
            "Title: ${book.title}, Author: ${book.author}"
        }
    }
}
