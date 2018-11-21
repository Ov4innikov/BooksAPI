package ru.booksapi.interfaces;

import ru.booksapi.entities.Genre;
import ru.booksapi.exceptions.ServiceExeption;

import java.util.List;
import java.util.Map;

/**
 * Interface for service of genres
 *
 * @author Eugene Ovchinnikov
 */
public interface GenreService {

    public Map<Integer,Map<String,String>> getGenreById(Long id) throws ServiceExeption;
    public Map<Integer,Map<String,String>> getAllGenres() throws ServiceExeption;
    public void updateGenreById(Map<String,String> map) throws ServiceExeption;
    public void putNewGenre(Map<String,String> map)  throws ServiceExeption;
    public void deleteGenreById(Map<String,String> map) throws ServiceExeption;
}
