package ouzel.library.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ouzel.library.model.Book;

import static org.junit.jupiter.api.Assertions.*;

class LibraryManagerTest {
    @Test
    public void checkAddingBook(){
        LibraryManager manager = new LibraryManager();
        manager.addBook(new Book("abc", "def", 1234));
        boolean answer = false;
        for (Book book: manager.getListOfBooks(true)){
            if (book.getTitle().equals("abc") && book.getAuthor().equals("def") &&
                    book.getYearOfPublication() == 1234){
                answer = true;
            }
        }
        assertTrue(answer);
    }

}