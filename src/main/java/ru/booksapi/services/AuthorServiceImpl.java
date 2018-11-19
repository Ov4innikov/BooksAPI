package ru.booksapi.services;

import org.springframework.stereotype.Service;
import ru.booksapi.entities.Author;
import ru.booksapi.interfaces.AuthorServce;

import java.util.List;

/**
 * Service of books authors
 *
 * @author Eugene Ovchinnikov
 */
@Service
public class AuthorServiceImpl implements AuthorServce {


    @Override
    public Author getAuthorById() {
        return null;
    }

    @Override
    public List<Author> getAllAuthors() {
        return null;
    }
}
