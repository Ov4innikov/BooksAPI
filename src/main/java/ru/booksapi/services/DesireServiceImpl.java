package ru.booksapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.booksapi.entities.Author;
import ru.booksapi.entities.Book;
import ru.booksapi.entities.Desire;
import ru.booksapi.entities.Genre;
import ru.booksapi.exceptions.ServiceExeption;
import ru.booksapi.interfaces.DesireService;
import ru.booksapi.repostitories.AuthorsRepository;
import ru.booksapi.repostitories.BooksRepository;
import ru.booksapi.repostitories.DesiresRepository;
import ru.booksapi.repostitories.GenresRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Service of booksapi desires
 *
 * @author Eugene Ovchinnikov
 */
@Service
public class DesireServiceImpl implements DesireService {

    private static final Logger logger = LoggerFactory.getLogger(DesireServiceImpl.class);

    @Autowired
    private DesiresRepository desiresRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private GenresRepository genresRepository;

    @Override
    public Map<Integer, Map<String, String>> getDesireById(Integer id) throws ServiceExeption {
        Map<Integer,Map<String,String>> resultMap = new HashMap<Integer,Map<String,String>>();
        Desire desire = null;
        if(desiresRepository.findById(id).isPresent()){
            desire = desiresRepository.findById(id).get();
            resultMap.put(id, convertDesireToMap(desire));
        }else {
            throw new ServiceExeption("Desire not found");
        }

        return resultMap;
    }

    @Override
    public Map<Integer, Map<String, String>> getDesiresByUserId(String id) throws ServiceExeption {
        Map<Integer,Map<String,String>> resultMap = new HashMap<Integer,Map<String,String>>();
        for(Desire item:desiresRepository.findAll()) {
            if(item.getUserId().equals(id.toString()))resultMap.put(item.getId(), convertDesireToMap(item));
        }
        logger.debug("RESULT MAP" + resultMap.toString());
        if (resultMap.size() == 0) throw new ServiceExeption("Empty desires result");
        return resultMap;
    }

    @Override
    public Map<Integer, Map<String, String>> getAllDesires() throws ServiceExeption {
        Map<Integer,Map<String,String>> resultMap = new HashMap<Integer,Map<String,String>>();
        for(Desire item:desiresRepository.findAll()) {
            resultMap.put(item.getId(), convertDesireToMap(item));
            logger.debug("Desire id is " + item.getId());
        }
        logger.debug("RESULT MAP" + resultMap.toString());
        if (resultMap.size() == 0) throw new ServiceExeption("Empty desires map");
        return resultMap;
    }

    @Override
    public void updateDesireById(Map<String, String> updatingDesire) throws ServiceExeption {
        Desire desire = null;
        Book book = null;
        if(desiresRepository.findById(Integer.valueOf(updatingDesire.get("id"))).isPresent()) {
            desire = desiresRepository.findById(Integer.valueOf(updatingDesire.get("id"))).get();
        }else {
            throw new ServiceExeption("Desire not found");
        }

        if(updatingDesire.get("bookId")!=null) {
            if(booksRepository.findById(Integer.valueOf(updatingDesire.get("bookId"))).isPresent()) {
                book = booksRepository.findById(Integer.valueOf(updatingDesire.get("bookId"))).get();
                desire.setBook(book);
            }else {
                throw new ServiceExeption("Desire not found");
            }
        }
        if(updatingDesire.get("userId")!=null) {
            desire.setUserId(updatingDesire.get("userId"));
        }
        desiresRepository.save(desire);
    }

    @Override
    public void putNewDesire(Map<String, String> newDesire) throws ServiceExeption {
        Desire desire = new Desire();
        desire.setUserId(newDesire.get("userId"));
        Book book = null;
        if(newDesire.get("bookId")!=null) {
            if(booksRepository.findById(Integer.valueOf(newDesire.get("bookId"))).isPresent()) {
                book = booksRepository.findById(Integer.valueOf(newDesire.get("bookId"))).get();
                desire.setBook(book);
            }else {
                throw new ServiceExeption("Desire not found");
            }
        }
        desiresRepository.save(desire);
    }

    @Override
    public void deleteDesireById(Map<String, String> deletedDesire) throws ServiceExeption {
        if(deletedDesire.isEmpty()) throw new ServiceExeption("Empty patameters");
        if(deletedDesire.get("id").isEmpty()) throw new ServiceExeption("Empty patameters");
        String id = deletedDesire.get("id");
        if(desiresRepository.findById(Integer.valueOf(id)).isPresent()) {
            desiresRepository.deleteById(Integer.valueOf(id));
        }else {
            throw new ServiceExeption("Desire not found");
        }
    }

    //Method for adding a desire
    public void createDesire(){
        Desire desire = new Desire();
        Author author = new Author();
        author.setLastName("Author last name 2");
        author.setFirstName("Author first name 2");
        author.setDateOfBirthDay(LocalDate.of(1900, 1,1));
        authorsRepository.save(author);
        logger.debug("AuthorId = " + author.getId());

        Genre genre = new Genre();
        genre.setGenre("Genre 2");
        genresRepository.save(genre);
        logger.debug("GenreId = " + genre.getId());

        Book book = new Book();
        book.setSeries("series 2");
        book.setName("Book name 2");
        book.setDescription("Description 2");
        book.setCountOfPage(2);
        book.setAuthor(author);
        book.setGenre(genre);

        desire.setUserId("User id");
        booksRepository.save(book);
        logger.debug("BookId = " + book.getId());
        desire.setBook(book);
        desiresRepository.save(desire);
    }

    private Map<String,String> convertDesireToMap(Desire desire){
        Map<String,String> resultMap = new HashMap<String,String>();
        Book book = desire.getBook();
        Author author = book.getAuthor();
        resultMap.put("userId",desire.getUserId());
        resultMap.put("bookId",book.getId().toString());
        resultMap.put("nameOdBook",book.getName());
        resultMap.put("firstNameOfBookAuthor",author.getFirstName());
        resultMap.put("cost",book.getCost());
        return resultMap;
    }


}
