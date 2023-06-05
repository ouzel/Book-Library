package ouzel.library.controller;


import ouzel.library.service.Response;


/*
Class for interaction with the resources of a library.
Helps with returning and borrowing the book.
 */
public final class Librarian {

    /*
    Trying to borrow the book (change the status of book to available = false, if the book exists).
     */
    public static Response borrowBook(BooksManager manager, String title, String author) {
        if (manager.borrowBook(title, author)) {
            return new Response("The book was borrowed successfully");
        }
        return new Response("The book cannot be borrowed");
    }


    /*
    Trying to return the book (change the status of book to available = true, if the book existed).
     */
    public static Response returnBook(BooksManager manager, String title, String author) {
        if (manager.returnBook(title, author)) {
            return new Response("The book was returned successfully");
        }
        return new Response("The book cannot be returned");
    }
}
