package ru.booksapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.booksapi.entities.Author;
import ru.booksapi.entities.Book;
import ru.booksapi.entities.Genre;
import ru.booksapi.exceptions.ServiceExeption;
import ru.booksapi.interfaces.GenreService;
import ru.booksapi.repostitories.GenresRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service of books genres
 *
 * @author Eugene Ovchinnikov
 */
@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenresRepository genresRepository;

    @Override
    public Map<Integer,Map<String,String>> getGenreById(Long id) throws ServiceExeption {
        Map<Integer,Map<String,String>> resultMap = new HashMap<Integer,Map<String,String>>();
        Genre genre = null;
        if(genresRepository.findById(id).isPresent()){
            genre = genresRepository.findById(id).get();
            resultMap.put(1, convertGenreToMap(genre));
        }else {
            throw new ServiceExeption("Genre not found");
        }

        return resultMap;
    }

    @Override
    public Map<Integer,Map<String,String>> getAllGenres() {
        return null;
    }

    @Override
    public void updateGenreById(Map<String, String> map) throws ServiceExeption {

    }

    @Override
    public void putNewGenre(Map<String, String> map) throws ServiceExeption {

    }

    @Override
    public void deleteGenreById(Map<String, String> map) throws ServiceExeption {

    }

    private Map<String,String> convertGenreToMap(Genre genre){
        Map<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("genre",genre.getGenre());
        return resultMap;
    }
}
