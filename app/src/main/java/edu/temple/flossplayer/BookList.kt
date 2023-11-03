import edu.temple.flossplayer.Book

class BookList {
    private val books: MutableList<Book> = mutableListOf()

    fun add(book: Book) {
        books.add(book)
    }
    fun getBooks(): List<Book> = books
    fun get(index: Int): Book {
        if (index in books.indices) {
            return books[index]
        } else {
            throw IndexOutOfBoundsException("Index: $index, Size: ${books.size}")
        }
    }
    fun size(): Int = books.size
}
