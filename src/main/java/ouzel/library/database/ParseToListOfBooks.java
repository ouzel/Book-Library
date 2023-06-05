package ouzel.library.database;

import ouzel.library.model.Book;

import java.util.List;


/*
Interface for publishing any type of database to the list of books.
 */
public interface ParseToListOfBooks {
    List<Book> getListOfBooks();
}
