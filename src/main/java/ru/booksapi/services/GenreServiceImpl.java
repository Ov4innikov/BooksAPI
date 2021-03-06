package ru.booksapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.booksapi.entities.Book;
import ru.booksapi.entities.Genre;
import ru.booksapi.exceptions.ServiceExeption;
import ru.booksapi.interfaces.GenreService;
import ru.booksapi.repostitories.BooksRepository;
import ru.booksapi.repostitories.GenresRepository;
import java.util.HashMap;
import java.util.Map;

/**
 * Service of booksapi genres
 *
 * @author Eugene Ovchinnikov
 */
@Service
public class GenreServiceImpl implements GenreService {

    private static final Logger logger = LoggerFactory.getLogger(GenreServiceImpl.class);

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private GenresRepository genresRepository;

    @Override
    public Map<Integer,Map<String,String>> getGenreById(Integer id) throws ServiceExeption {
        Map<Integer,Map<String,String>> resultMap = new HashMap<Integer,Map<String,String>>();
        Genre genre = null;
        if(genresRepository.findById(id).isPresent()){
            genre = genresRepository.findById(id).get();
            resultMap.put(id, convertGenreToMap(genre));
        }else {
            throw new ServiceExeption("Genre not found");
        }

        return resultMap;
    }

    @Override
    public Map<Integer,Map<String,String>> getAllGenres() throws ServiceExeption {
        Map<Integer,Map<String,String>> resultMap = new HashMap<Integer,Map<String,String>>();
        for(Genre item:genresRepository.findAll()) {
            resultMap.put(item.getId(), convertGenreToMap(item));
            logger.debug("Author id is " + item.getId());
        }
        logger.debug("RESULT MAP" + resultMap.toString());
        if (resultMap.size() == 0) throw new ServiceExeption("Empty genres map");
        return resultMap;
    }

    @Override
    public void updateGenreById(Map<String, String> updatingGenre) throws ServiceExeption {
        Genre genre = null;
        if(genresRepository.findById(Integer.valueOf(updatingGenre.get("id"))).isPresent()) {
            genre = genresRepository.findById(Integer.valueOf(updatingGenre.get("id"))).get();
        }else {
            throw new ServiceExeption("Genre not found");
        }
        if(updatingGenre.get("genre")!=null) {
            genre.setGenre(updatingGenre.get("genre"));
        }
        genresRepository.save(genre);
    }

    @Override
    public void putNewGenre(Map<String, String> newGenre) throws ServiceExeption {
        Genre genre = new Genre();
        for(Genre item:genresRepository.findAll()) {
            if(item.getGenre().equals(newGenre.get("genre"))) throw new ServiceExeption("Genre was created");
        }
        genre.setGenre(newGenre.get("genre"));

        genresRepository.save(genre);
    }

    @Override
    public void deleteGenreById(Map<String, String> deletedGenre) throws ServiceExeption {
        if(deletedGenre.isEmpty()) throw new ServiceExeption("Empty patameters");
        if(deletedGenre.get("id").isEmpty()) throw new ServiceExeption("Empty patameters");
        String id = deletedGenre.get("id");
        for(Book item:booksRepository.findAll()) {
            if(item.getId()==Integer.valueOf(id)) throw new ServiceExeption("Some book is link with this genre!");
        }
        if(genresRepository.findById(Integer.valueOf(deletedGenre.get("id"))).isPresent()) {
            genresRepository.deleteById(Integer.valueOf(id));
        }else {
            throw new ServiceExeption("Genre not found");
        }

    }

    private Map<String,String> convertGenreToMap(Genre genre){
        Map<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("genre",genre.getGenre());
        return resultMap;
    }
}
