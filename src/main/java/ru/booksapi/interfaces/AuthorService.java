package ru.booksapi.interfaces;

import ru.booksapi.entities.Author;
import ru.booksapi.exceptions.ServiceExeption;

import java.util.List;
import java.util.Map;

/**
 * Interface for service of books authors
 *
 * @author Eugene Ovchinnikov
 */
public interface AuthorService {

    public Map<Integer,Map<String,String>> getAuthorById(Long id) throws ServiceExeption;
    public Map<Integer,Map<String,String>> getAllAuthors() throws ServiceExeption;
    public void updateAuthorById(Map<String,String> map) throws ServiceExeption;
    public void putNewAuthor(Map<String,String> map)  throws ServiceExeption;
    public void deleteAuthorById(Map<String,String> map) throws ServiceExeption;
}
