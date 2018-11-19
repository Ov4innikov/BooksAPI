package ru.booksapi.interfaces;

import ru.booksapi.entities.Author;

import java.util.List;

/**
 * Interface for service of books authors
 *
 * @author Eugene Ovchinnikov
 */
public interface AuthorServce {

    public Author getAuthorById();
    public List<Author> getAllAuthors();
}
