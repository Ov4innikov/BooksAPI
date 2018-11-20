package ru.booksapi.services;

import org.springframework.stereotype.Service;
import ru.booksapi.entities.Genre;
import ru.booksapi.interfaces.GenreService;

import java.util.List;
import java.util.Map;

/**
 * Service of books genres
 *
 * @author Eugene Ovchinnikov
 */
@Service
public class GenreServiceImpl implements GenreService {
    @Override
    public Map<Integer,Map<String,String>> getGenreById(Long id) {
        return null;
    }

    @Override
    public Map<Integer,Map<String,String>> getAllGenres() {
        return null;
    }
}
