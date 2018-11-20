package ru.booksapi.services;

import org.springframework.stereotype.Service;
import ru.booksapi.entities.Author;
import ru.booksapi.interfaces.AuthorServce;

import java.util.List;
import java.util.Map;

/**
 * Service of books authors
 *
 * @author Eugene Ovchinnikov
 */
@Service
public class AuthorServiceImpl implements AuthorServce {


    @Override
    public Map<Integer,Map<String,String>> getAuthorById(Long id) {
        return null;
    }

    @Override
    public Map<Integer,Map<String,String>> getAllAuthors() {
        return null;
    }
}
