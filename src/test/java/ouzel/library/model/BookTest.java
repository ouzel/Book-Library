package ouzel.library.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    public void initializationExceptionWrongTitle() {
        Assertions.assertThrows(IllegalArgumentException.class,
                ()->  new Book(" ", "author", 2012));
    }


    @Test
    public void initializationExceptionWrongAuthor() {
        Assertions.assertThrows(IllegalArgumentException.class,
                ()->  new Book("dfghjk", "      ", 2012));
    }


    @Test
    public void initializationExceptionWrongYear() {
        Assertions.assertThrows(IllegalArgumentException.class,
                ()->  new Book("dfghjk", "ertyui", -187));
    }


    public void checkIsAvailable(){
        Book book = new Book("ghj", "ghuio", 1243);
        Assertions.assertEquals(book.isAvailable(), true);
        book.available = false;
        Assertions.assertEquals(book.isAvailable(), false);
    }

}