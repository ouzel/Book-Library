package ouzel.library.database;

import ouzel.library.model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


/*
Class for parsing the csv table with the columns: title, author, year of publication.
 */
public class ParseFromCSVToListOfBooks implements ParseToListOfBooks {
    String filepath;

    // Exceptions when parsing or reading a file.
    List<Exception> exceptions;


    /*
    Constructor gets a filepath to the csv file.
     */
    public ParseFromCSVToListOfBooks(String filepath) {
        File f = new File(filepath);
        if (!f.exists() || f.isDirectory()) {
            throw new NoSuchElementException("The file does not exist");
        } else if (!filepath.endsWith(".csv")) {
            throw new IllegalArgumentException("The file should be in csv format");
        }
        this.filepath = filepath;
        exceptions = new ArrayList<>();
    }


    @Override
    public List<Book> getListOfBooks() {
        List<Book> collection = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));

            try {
                String input;
                while ((input = reader.readLine()) != null) {
                    String[] bookInfo = input.split(",");
                    try {
                        int dateOfPublication = Integer.parseInt(bookInfo[2]);
                        Book book = new Book(bookInfo[0], bookInfo[1], dateOfPublication);
                        collection.add(book);
                    } catch (NumberFormatException e) {
                        exceptions.add(e);
                    }
                }
                reader.close();
            } catch (IOException e) {
                exceptions.add(e);
            }

        } catch (FileNotFoundException e) {
            exceptions.add(e);
        }
        return collection;
    }
}
