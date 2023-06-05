package ouzel.library.service;

import ouzel.library.controller.BooksManager;
import ouzel.library.model.Book;
import ouzel.library.controller.Librarian;
import ouzel.library.controller.LibraryManager;
import ouzel.library.database.ParseFromCSVToListOfBooks;
import ouzel.library.database.ParseToListOfBooks;

import java.util.List;
import java.util.Scanner;


/*
Class for console interaction with the user.
 */
public class UserInteraction {
    BooksManager manager;
    Scanner reader;

    public UserInteraction() {
        manager = new LibraryManager();
        reader = new Scanner(System.in);
        fillLibraryWithBooks();
        start();
    }


    /*
    Getting the list of books from the csv database.
     */
    public void fillLibraryWithBooks() {
        ParseToListOfBooks parser = new ParseFromCSVToListOfBooks(
                getClass().getClassLoader().getResource("books.csv").getPath());
        for (Book book : parser.getListOfBooks()) {
            manager.addBook(book);
        }
    }


    public void start() {
        System.out.println("Welcome to the library.");
        System.out.println("Choose one of the following operations:");
        System.out.println("1: view all the books in the library");
        System.out.println("2: view all the available books in a library");
        System.out.println("3: borrow a book from a library");
        System.out.println("4: return the book to the library");
        System.out.println("-q: close the library");
        readCommand();
    }


    public void readCommand() {
        String command = reader.nextLine().trim();
        while (!command.equals("-q")) {
            if (command.equals("1")) {
                viewAllBooks();
            } else if (command.equals("2")) {
                viewAvailableBooks();
            } else if (command.equals("3")) {
                borrowBookFromLibrary();
            } else if (command.equals("4")) {
                returnBookFromLibrary();
            } else {
                System.out.println("Input again");
            }
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("Choose one of the following operations and input its number:");
            System.out.println("1: view all the books in the library");
            System.out.println("2: view all the available books in a library");
            System.out.println("3: borrow a book from a library");
            System.out.println("4: return the book to the library");
            System.out.println("-q: close the library");
            command = reader.nextLine();
        }
    }


    /*
    Prints all books to the console and the total number of books.
     */
    public void viewAllBooks() {
        for (Book book : manager.getListOfBooks(true)) {
            System.out.println(book);
        }
        for (Book book : manager.getListOfBooks(false)) {
            System.out.println(book);
        }
        int total = manager.getListOfBooks(true).size() + manager.getListOfBooks(false).size();
        System.out.println("Total: " + total + " books.");
    }


    /*
    Prints the books available for borrowing.
     */
    public void viewAvailableBooks() {
        for (Book book : manager.getListOfBooks(true)) {
            System.out.println(book);
        }
        System.out.println("Total: " + manager.getListOfBooks(true).size() + " books.");
    }


    /*
    The process of borrowing the book from the library.
     */
    public void borrowBookFromLibrary() {
        System.out.println("Input the keyword of the book: ");
        String keyword = reader.nextLine();
        System.out.println("Possible books: ");
        List<Book> possible = manager.getBooks(keyword, true);
        if (possible.size() < 1) {
            System.out.println("No books with such keyword were found");
        } else {
            for (Book book : possible) {
                System.out.println(book);
            }
            System.out.println();
            System.out.println("To borrow the book, input 1. Else input anything else");
            String input = reader.nextLine();
            if (input.trim().equals("1")) {
                System.out.println("Input title: ");
                String title = reader.nextLine();
                System.out.println("Input author: ");
                String author = reader.nextLine();
                System.out.println(Librarian.borrowBook(manager, title, author).getMessage());
            }
        }
    }


    /*
    The process of returning the book to the library.
     */
    public void returnBookFromLibrary() {
        System.out.println("Borrowed books: ");
        List<Book> possible = manager.getListOfBooks(false);
        if (possible.size() < 1) {
            System.out.println("There are no borrowed books");
        } else {
            for (Book book : possible) {
                System.out.println(book);
            }
            System.out.println();
            System.out.println("To return the book, input 1. Else input anything else");
            String input = reader.nextLine();
            if (input.trim().equals("1")) {
                System.out.println("Input title: ");
                String title = reader.nextLine();
                System.out.println("Input author: ");
                String author = reader.nextLine();
                System.out.println(Librarian.returnBook(manager, title, author).getMessage());
            }
        }
    }
}
