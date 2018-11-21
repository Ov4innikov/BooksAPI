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
        if(desiresRepository.findById(Integer.valueOf(updatingDesire.get("id"))).isPresent()) {
            desire = desiresRepository.findById(Integer.valueOf(updatingDesire.get("id"))).get();
        }else {
            throw new ServiceExeption("Desire not found");
        }
        if(updatingDesire.get("bookId")!=null) {
            desire.setBookId(Integer.valueOf(updatingDesire.get("bookId")));
        }
        if(updatingDesire.get("userId")!=null) {
            desire.setUserId(updatingDesire.get("userId"));
        }
        desiresRepository.save(desire);
    }

    @Override
    public void putNewDesire(Map<String, String> newGenre) throws ServiceExeption {
        Desire desire = new Desire();
        desire.setUserId(newGenre.get("userId"));
        desire.setBookId(Integer.valueOf(newGenre.get("bookId")));

        desiresRepository.save(desire);
    }

    @Override
    public void deleteDesireById(Map<String, String> map) throws ServiceExeption {

    }

    private Map<String,String> convertDesireToMap(Desire desire){
        Map<String,String> resultMap = new HashMap<String,String>();
        Integer bookId = desire.getBookId();
        Book book = booksRepository.findById(bookId).get();
        Integer authorId = book.getId();
        Author author = authorsRepository.findById(authorId).get();
        resultMap.put("userId",desire.getUserId());
        resultMap.put("bookId",bookId.toString());
        resultMap.put("nameOdBook",book.getName());
        resultMap.put("firstNameOfBookAuthor",author.getFirstName());
        resultMap.put("cost",book.getCost());
        return resultMap;
    }
}
