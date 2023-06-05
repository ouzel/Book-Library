package ouzel.library.controller;

import ouzel.library.model.Book;

import java.util.List;


/*
Interface for managing any type of book collection.
 */
public interface BooksManager {
    List<Book> getListOfBooks(boolean available);


    // Get books with the title containing a special keyword.
    List<Book> getBooks(String keyword, boolean available);

    boolean borrowBook(String title, String author);

    boolean returnBook(String title, String author);


    // Adding a new book to the collection.
    void addBook(Book book);
}
