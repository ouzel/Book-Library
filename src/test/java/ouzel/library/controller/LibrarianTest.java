package ouzel.library.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ouzel.library.model.Book;
import ouzel.library.service.Response;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianTest {
    @Test
    public void checkTheBorrowingProcessEmptyLibrary() {
        BooksManager manager = new LibraryManager();
        Response response = Librarian.borrowBook(manager, "dfghjkl", "wertyu");
        Assertions.assertEquals(response.getMessage(), "The book cannot be borrowed");
    }

    @Test
    public void checkTheBorrowingProcessWrongBook() {
        BooksManager manager = new LibraryManager();
        manager.addBook(new Book("The Testing", "Joelle Charbonneau", 2013));
        manager.addBook(new Book("Darkfall", "Dean Koontz", 1984));
        manager.addBook(new Book("By the Sword", "Mercedes Lackey", 1991));
        Response response = Librarian.borrowBook(manager, "dfghjkl", "wertyu");
        Assertions.assertEquals(response.getMessage(), "The book cannot be borrowed");
    }

    @Test
    public void checkTheBorrowingProcessRightBook() {
        BooksManager manager = new LibraryManager();
        manager.addBook(new Book("The Testing", "Joelle Charbonneau", 2013));
        manager.addBook(new Book("Darkfall", "Dean Koontz", 1984));
        manager.addBook(new Book("By the Sword", "Mercedes Lackey", 1991));
        Response response = Librarian.borrowBook(manager, "Darkfall", "Dean Koontz");
        Assertions.assertEquals(response.getMessage(), "The book was borrowed successfully");
    }

    @Test
    public void checkTheReturningProcessEmptyLibrary() {
        BooksManager manager = new LibraryManager();
        Response response = Librarian.returnBook(manager, "dfghjkl", "wertyu");
        Assertions.assertEquals(response.getMessage(), "The book cannot be returned");
    }

    @Test
    public void checkTheReturningProcessWrongBook() {
        BooksManager manager = new LibraryManager();
        manager.addBook(new Book("The Testing", "Joelle Charbonneau", 2013));
        manager.addBook(new Book("Darkfall", "Dean Koontz", 1984));
        manager.addBook(new Book("By the Sword", "Mercedes Lackey", 1991));
        Response response = Librarian.returnBook(manager, "dfghjkl", "wertyu");
        Assertions.assertEquals(response.getMessage(), "The book cannot be returned");
    }

    @Test
    public void checkTheReturningProcessRightBook() {
        BooksManager manager = new LibraryManager();
        manager.addBook(new Book("The Testing", "Joelle Charbonneau", 2013));
        manager.addBook(new Book("Darkfall", "Dean Koontz", 1984));
        manager.addBook(new Book("By the Sword", "Mercedes Lackey", 1991));
        for (Book book : manager.getListOfBooks(true)) {
            if (book.getTitle().equals("Darkfall")) {
                book.available = false;
            }
        }
        Response response = Librarian.returnBook(manager, "Darkfall", "Dean Koontz");
        Assertions.assertEquals(response.getMessage(), "The book was returned successfully");
    }
}