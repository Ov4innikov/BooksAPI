package ru.booksapi.interfaces;

import java.util.Map;
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

    public Map<Integer,Map<String,String>> getBookById(Long id) throws ServiceExeption;
    public Map<Integer,Map<String,String>> getBooksByAuthorId(Long authorId) throws ServiceExeption;
    public Map<Integer,Map<String,String>> getBooksByGenreId(Long genreId) throws ServiceExeption;
    public Map<Integer,Map<String,String>> getAllBooks() throws ServiceExeption;
    public void updateBookById(Map<String,String> map) throws ServiceExeption;



    public void createBook();//Method for adding a book
}
