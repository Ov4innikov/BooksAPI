package ru.booksapi.interfaces;

import ru.booksapi.entities.Genre;

import java.util.List;
import java.util.Map;

/**
 * Interface for service of genres
 *
 * @author Eugene Ovchinnikov
 */
public interface GenreService {

    public Map<Integer,Map<String,String>> getGenreById(Long id);
    public Map<Integer,Map<String,String>> getAllGenres();
}
