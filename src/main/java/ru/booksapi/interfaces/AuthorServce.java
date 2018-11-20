package ru.booksapi.interfaces;

import ru.booksapi.entities.Author;

import java.util.List;
import java.util.Map;

/**
 * Interface for service of books authors
 *
 * @author Eugene Ovchinnikov
 */
public interface AuthorServce {

    public Map<Integer,Map<String,String>> getAuthorById(Long id);
    public Map<Integer,Map<String,String>> getAllAuthors();
}
