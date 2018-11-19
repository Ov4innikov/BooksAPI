package ru.booksapi.services;

import org.springframework.stereotype.Service;
import ru.booksapi.entities.Genre;
import ru.booksapi.interfaces.GenreService;

import java.util.List;

/**
 * Service of books genres
 *
 * @author Eugene Ovchinnikov
 */
@Service
public class GenreServiceImpl implements GenreService {
    @Override
    public Genre getGenreById() {
        return null;
    }

    @Override
    public List<Genre> getAllGenres() {
        return null;
    }
}
