package ru.booksapi.interfaces;

import ru.booksapi.entities.Author;
import ru.booksapi.entities.Book;
import ru.booksapi.entities.Genre;
import ru.booksapi.exceptions.ServiceExeption;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for service of books
 *
 * @author Eugene Ovchinnikov
 */
public interface BookService {

    public Book getBookById();
    public List<Book> getBooksByAuthor();
    public List<Book> getBooksByGenre();
    public List<Book> getAllBooks() throws ServiceExeption;
}
