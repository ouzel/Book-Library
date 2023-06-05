package ouzel.library.controller;

import ouzel.library.model.Book;

import java.util.ArrayList;
import java.util.List;


/*
Library manager of the collection of books.
 */
public class LibraryManager implements BooksManager {
    // The managed collection of books.
    private List<Book> bookCollection;

    public LibraryManager() {
        bookCollection = new ArrayList<>();
    }


    /*
    Return all the books in the collection.
     */
    @Override
    public List<Book> getListOfBooks(boolean available) {
        List<Book> listOfBooks = new ArrayList<>();
        for (Book book : bookCollection) {
            if (book.isAvailable() == available) {
                listOfBooks.add(book);
            }
        }
        return listOfBooks;
    }


    /*
    Get a list of books containing a specific keyword.
     */
    @Override
    public List<Book> getBooks(String keyword, boolean available) {
        List<Book> listOfBooks = new ArrayList<>();
        for (Book book : bookCollection) {
            if (book.isAvailable() == available &&
                    book.getTitle().toLowerCase().contains(keyword.toLowerCase().trim())) {
                listOfBooks.add(book);
            }
        }
        return listOfBooks;
    }


    @Override
    public boolean borrowBook(String title, String author) {
        for (Book book : bookCollection) {
            if (book.getTitle().trim().equals(title.trim()) && book.getAuthor().trim().equals(author.trim()) &&
                    book.isAvailable()) {
                book.available = false;
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean returnBook(String title, String author) {
        for (Book book : bookCollection) {
            if (book.getTitle().trim().equals(title.trim()) && book.getAuthor().trim().equals(author.trim()) &&
                    !book.isAvailable()) {
                book.available = true;
                return true;
            }
        }
        return false;
    }


    /*
    Adding a new book to the collection (1 at a time).
     */
    @Override
    public void addBook(Book book) {
        bookCollection.add(book);
    }
}
