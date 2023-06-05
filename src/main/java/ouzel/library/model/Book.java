package ouzel.library.model;

import java.time.LocalDate;

public class Book {
    private final String title;
    private final String author;
    private final int yearOfPublication;

    // Field for controlling whether the book is in the library or not.
    public boolean available;

    public Book(String title, String author, int dateOfPublication) {
        if (title.trim().equals("") || author.trim().equals("")) {
            throw new IllegalArgumentException("Title and author cannot be null");
        } else if (dateOfPublication < 0){
            throw new IllegalArgumentException("Year of publication more than 0");
        }
        this.title = title;
        this.author = author;
        this.yearOfPublication = dateOfPublication;
        available = true;
    }



    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return String.format("\"%s\" by %s, published on %d", title, author, yearOfPublication);
    }
}
