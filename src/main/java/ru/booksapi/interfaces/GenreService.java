package ru.booksapi.interfaces;

import ru.booksapi.entities.Genre;

import java.util.List;

/**
 * Interface for service of genres
 *
 * @author Eugene Ovchinnikov
 */
public interface GenreService {

    public Genre getGenreById();
    public List<Genre> getAllGenres();
}
